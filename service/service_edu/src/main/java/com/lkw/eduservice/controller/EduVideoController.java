package com.lkw.eduservice.controller;

import com.lkw.cmsservice.commonutils.R;
import com.lkw.eduservice.client.VodClient;
import com.lkw.eduservice.entity.EduVideo;
import com.lkw.eduservice.service.EduVideoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }

    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }


    //删除小节
    //需要完善,删小节时,把视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)){
            //根据视频id,远程调用实现视频删除
            vodClient.removeAliyunVideo(videoSourceId);//视频id String
        }
        videoService.removeById(id);
        return R.ok();
    }
}
