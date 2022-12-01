package com.lkw.cmsservice.controller;



import com.lkw.cmsservice.commonutils.R;
import com.lkw.cmsservice.entity.CrmBanner;
import com.lkw.cmsservice.service.CrmBannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
@Slf4j
public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;
    //查询所有banner
    @GetMapping("getAllBanner")
    public R getAllBanner(){
        log.info("获取所有banner");
        List<CrmBanner> List =crmBannerService.selectAllBanner();
        return R.ok().data("list",List);
    }
}

