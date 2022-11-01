package com.lkw.eduservice.mapper;

import com.lkw.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lkw.eduservice.entity.vo.CoursePublishVo;

/**
* @author 李可文
* @description 针对表【edu_course(课程)】的数据库操作Mapper
* @createDate 2022-10-06 15:55:48
* @Entity com.lkw.eduservice.entity.EduCourse
*/
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);
}




