package com.lkw.eduservice.controller;

import com.lkw.commonutils.R;
import com.lkw.eduservice.entity.vo.CourseInfoVo;
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

}
