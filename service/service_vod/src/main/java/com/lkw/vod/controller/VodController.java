package com.lkw.vod.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.lkw.commonutils.R;
import com.lkw.servicebase.exceptionhandler.GuliException;
import com.lkw.vod.Utils.ConstantVodUtils;
import com.lkw.vod.Utils.InitVodCilent;
import com.lkw.vod.service.VodService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/eduvod/video")
public class VodController {


    //上传视频到阿里云

    @Autowired
    private VodService vodService;




    //http://localhost:8003/eduvod/video/uploadAliyunVideo
    @PostMapping("uploadAliyunVideo")
    public R uploadAliyunVideo(@ApiParam(value = "视频", required = true)@RequestPart("file")MultipartFile file){
        System.out.println(file);
        String videoId=vodService.uploadAliyunVideo(file);
         return R.ok().data("videoId",videoId);
    }


    @DeleteMapping("{id}")
    public R removeAliyunVideo(@PathVariable String id){
        try{
            DefaultAcsClient defaultAcsClient = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建一个删除视频request对象
            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            //设置id
            deleteVideoRequest.setVideoIds(id);
            //获取结果
            DeleteVideoResponse acsResponse = defaultAcsClient.getAcsResponse(deleteVideoRequest);
            return R.ok();
            //初始化对象
        }catch (Exception e){
            throw new GuliException(20001,"删除视频失败");
        }

    }



    //删除多个视频的方法
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List videoIdList) {

        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }


}
