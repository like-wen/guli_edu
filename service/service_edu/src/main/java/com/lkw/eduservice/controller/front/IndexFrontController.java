package com.lkw.eduservice.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lkw.cmsservice.commonutils.R;
import com.lkw.eduservice.entity.EduCourse;
import com.lkw.eduservice.entity.EduTeacher;
import com.lkw.eduservice.service.EduCourseService;
import com.lkw.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {
    //查询

    @Autowired
    EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询前八条记录
    @GetMapping("index")
    public R index(){
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> courseList = eduCourseService.list(courseQueryWrapper);

        //查询前四个名师
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<EduTeacher> teacherList = eduTeacherService.list(teacherQueryWrapper);

        return R.ok().data("eduList",courseList).data("teacherList",teacherList);
    }
}
