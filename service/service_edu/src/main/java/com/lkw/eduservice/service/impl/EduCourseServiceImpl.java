package com.lkw.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkw.eduservice.entity.EduCourse;
import com.lkw.eduservice.entity.EduCourseDescription;
import com.lkw.eduservice.entity.vo.CourseInfoVo;
import com.lkw.eduservice.entity.vo.CoursePublishVo;
import com.lkw.eduservice.service.EduCourseDescriptionService;
import com.lkw.eduservice.service.EduCourseService;
import com.lkw.eduservice.mapper.EduCourseMapper;
import com.lkw.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 李可文
* @description 针对表【edu_course(课程)】的数据库操作Service实现
* @createDate 2022-10-06 15:55:48
*/
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
    implements EduCourseService{



    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

        //Vo转换成entity类
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse );

        int insert = baseMapper.insert(eduCourse);
        if(insert<=0){
            //添加失败
            throw new GuliException(20001,"添加课程失败");
        }
        //添加成功获取id
        String cid = eduCourse.getId();

        //课程描述
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }

    //根据课程id查询课程基本信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        //查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }


    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new GuliException(20001, "修改课程信息失败");
        }

        //2 修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        //调用mapper
        return baseMapper.getPublishCourseInfo(id);
    }
}




