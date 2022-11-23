package com.lkw.eduservice.controller;

import com.lkw.commonutils.R;
import com.lkw.eduservice.entity.EduCourse;
import com.lkw.eduservice.entity.vo.CourseInfoVo;
import com.lkw.eduservice.entity.vo.CoursePublishVo;
import com.lkw.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/course")
public class EduCourseController {



    @Autowired
    private EduCourseService courseService;
    //添加课程基本信息的方法




    @GetMapping
    public R getCourseList() {
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list", list);
    }

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

    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public R pubishCourse(@PathVariable String id){
        EduCourse eduCourse=new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }


    //删除课程
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
        return R.ok();

    }
}
