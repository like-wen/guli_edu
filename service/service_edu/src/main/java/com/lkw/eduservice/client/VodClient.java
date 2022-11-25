package com.lkw.eduservice.client;


import com.lkw.commonutils.R;
import com.lkw.eduservice.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name="service-vod",fallback = VodClientImpl.class)//被调用的服务的名字,熔断器的实现类
@Component
public interface VodClient {
    //定义方法路径
    //把service_vod的方法完完全全的复制来,路径改成完全路径
    @DeleteMapping(value="eduvod/video/{id}")
    public R removeAliyunVideo(@PathVariable("id") String id);


    //删除多个视频的方法
    @DeleteMapping("eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);


}
