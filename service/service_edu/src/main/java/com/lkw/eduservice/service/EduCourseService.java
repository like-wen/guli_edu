package com.lkw.eduservice.service;

import com.lkw.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lkw.eduservice.entity.vo.CourseInfoVo;
import com.lkw.eduservice.entity.vo.CoursePublishVo;

/**
* @author 李可文
* @description 针对表【edu_course(课程)】的数据库操作Service
* @createDate 2022-10-06 15:55:48
*/
public interface EduCourseService extends IService<EduCourse> {

   //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);


    CoursePublishVo publishCourseInfo(String id);
}
