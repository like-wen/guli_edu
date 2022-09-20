package com.lkw.eduservice.controller;

import com.lkw.commonutils.R;
import com.lkw.eduservice.mapper.EduSubjectMapper;
import com.lkw.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin//跨域
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;
    //添加课程分类
    //获取上传文件,读取文件

    @PostMapping("addSubject")
    public R addSubject(@ApiParam(value = "excel文件", required = true)@RequestPart("file")MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }
}
