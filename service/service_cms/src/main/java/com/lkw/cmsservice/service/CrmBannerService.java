package com.lkw.cmsservice.service;

import com.lkw.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 李可文
* @description 针对表【crm_banner(首页banner表)】的数据库操作Service
* @createDate 2022-11-27 11:54:34
*/
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectAllBanner();
}
