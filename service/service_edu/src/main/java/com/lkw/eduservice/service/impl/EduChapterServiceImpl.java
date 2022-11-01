package com.lkw.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkw.eduservice.entity.EduChapter;
import com.lkw.eduservice.entity.EduVideo;
import com.lkw.eduservice.entity.chapter.ChapterVo;
import com.lkw.eduservice.entity.chapter.VideoVo;
import com.lkw.eduservice.service.EduChapterService;
import com.lkw.eduservice.mapper.EduChapterMapper;
import com.lkw.eduservice.service.EduVideoService;
import com.lkw.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 李可文
* @description 针对表【edu_chapter(课程)】的数据库操作Service实现
* @createDate 2022-10-07 16:40:12
*/
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter>
    implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo>  getChapterVideoByCourseId(String courseId) {
        //1根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(chapterQueryWrapper);
        //2根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(videoQueryWrapper);
        //创建list集合进行最终封装
        ArrayList<ChapterVo> finalList = new ArrayList<>();
        //3遍历查询章节list集合进行封装
        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalList.add(chapterVo);

            ArrayList<VideoVo> finalVideoVoList = new ArrayList<>();
            //4遍历查询小节list集合进行封装
            for (int j = 0; j < eduVideoList.size(); j++) {
                EduVideo eduVideo = eduVideoList.get(j);
                if (eduVideo.getChapterId().equals(chapterVo.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    finalVideoVoList.add(videoVo);
                }
            }

            chapterVo.setChildren(finalVideoVoList);
        }


        return finalList;
    }

    //删除章节的方法
    @Override
    public boolean deleteChapterById(String chapterId) {
        //根据章节id，如果有小节数据则不能删除
        QueryWrapper<EduVideo> eduVideoWrapper = new QueryWrapper<>();
        eduVideoWrapper.eq("chapter_id", chapterId);
        int count =(int) eduVideoService.count(eduVideoWrapper);
        if (count > 0) {
            //有小节
            throw new GuliException(20001, "有小节，无法删除");
        }
        int i = baseMapper.deleteById(chapterId);

        return i > 0;
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        baseMapper.delete(queryWrapper);
    }


}




