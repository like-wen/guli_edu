package com.lkw.eduservice.service;

import com.lkw.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lkw.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author 李可文
* @description 针对表【edu_subject(课程科目)】的数据库操作Service
* @createDate 2022-09-20 21:04:48
*/
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();

}
