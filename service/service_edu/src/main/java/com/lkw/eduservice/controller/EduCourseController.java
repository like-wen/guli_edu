package com.lkw.eduservice.controller;

import com.lkw.commonutils.R;
import com.lkw.eduservice.entity.vo.CourseInfoVo;
import com.lkw.eduservice.entity.vo.CoursePublishVo;
import com.lkw.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/course")
public class EduCourseController {



    @Autowired
    private EduCourseService courseService;
    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        String id=courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }


    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo=courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }


    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){


        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){

    CoursePublishVo coursePublishVo=courseService.publishCourseInfo(id);
    return R.ok().data("publishCourse",coursePublishVo);
    }


}
