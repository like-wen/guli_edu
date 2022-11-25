package com.lkw.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkw.eduservice.client.VodClient;
import com.lkw.eduservice.entity.EduVideo;
import com.lkw.eduservice.service.EduVideoService;
import com.lkw.eduservice.mapper.EduVideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 李可文
* @description 针对表【edu_video(课程视频)】的数据库操作Service实现
* @createDate 2022-10-07 16:39:16
*/
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo>
    implements EduVideoService{

    //注入
    @Autowired
    VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String courseId) {
        //根据课程id查询课程所有视频id
        QueryWrapper<EduVideo> wrapperVod=new QueryWrapper<>();
        wrapperVod.eq("course_id",courseId);
        wrapperVod.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVod);
        List<String> videoIds = eduVideoList.stream().map(EduVideo::getVideoSourceId).collect(Collectors.toList());

        vodClient.deleteBatch(videoIds);

        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
        //TODO 删除对应视频的文件
    }
}




