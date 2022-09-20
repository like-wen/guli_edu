package com.lkw.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkw.eduservice.entity.EduSubject;
import com.lkw.eduservice.entity.excel.SubjectData;
import com.lkw.eduservice.listener.SubjectExcelListener;
import com.lkw.eduservice.service.EduSubjectService;
import com.lkw.eduservice.mapper.EduSubjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
* @author 李可文
* @description 针对表【edu_subject(课程科目)】的数据库操作Service实现
* @createDate 2022-09-20 21:04:48
*/
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject>
    implements EduSubjectService{


    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //输入流
            InputStream inputStream=file.getInputStream();
            //调用方法读取
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




