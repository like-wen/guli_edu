package com.lkw.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkw.cmsservice.entity.CrmBanner;
import com.lkw.cmsservice.service.CrmBannerService;
import com.lkw.cmsservice.mapper.CrmBannerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 李可文
* @description 针对表【crm_banner(首页banner表)】的数据库操作Service实现
* @createDate 2022-11-27 11:54:34
*/
@Transactional
@Service
@Slf4j
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Cacheable(value = "banner",key = "'selectIndexList'")
    //查询所有banner
    @Override
    public List<CrmBanner> selectAllBanner() {
        log.info("进行mysql查询并记录到redis");
        //根据id降序排列，显示排列之后的前两条记录
        QueryWrapper<CrmBanner> bannerQueryWrapper = new QueryWrapper<>();
        bannerQueryWrapper.orderByDesc("id");
        //list拼接sql语句
        bannerQueryWrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}


