package com.lkw.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkw.eduservice.service.EduSubjectService;
import com.lkw.eduservice.entity.EduSubject;
import com.lkw.eduservice.entity.excel.SubjectData;
import com.lkw.eduservice.entity.subject.OneSubject;
import com.lkw.eduservice.entity.subject.TwoSubject;
import com.lkw.eduservice.listener.SubjectExcelListener;
import com.lkw.eduservice.mapper.EduSubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
* @author 李可文
* @description 针对表【edu_subject(课程科目)】的数据库操作Service实现
* @createDate 2022-09-20 21:04:48
*/
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject>
    implements EduSubjectService {


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
    //课程分类列表（树形）
    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //1、查出所有一级分裂 parentid=0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //2、查出所有二级分类 parentid!=0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3、封装一级分类
        //查出出所有的一级分类list集合遍历，得到每一个一级分类对象，获取每一个分类对象值
        //封装到要求的list集合里面List<OneSubject> finalSubjectList
        for (int i = 0; i < oneSubjectList.size(); i++) {
            //得到oneSubjectList每个eduSubject
            EduSubject eduSubject = oneSubjectList.get(i);
            //把多个eduSubject里面值取出来，放到OneSubject对象里面
            OneSubject oneSubject = new OneSubject();
            //oneSubject.setId(eduSubject.getId());
            //oneSubject.setTitle(eduSubject.getTitle());
            //eduSubject值复制到oneSubject里面
            BeanUtils.copyProperties(eduSubject,oneSubject);
            //多个OneSubject放到finalSubjectList里面
            finalSubjectList.add(oneSubject);

            //4、封装二级分类
            //在一级分类循环遍历查询所有的二级分类
            //在创建list集合封装每一个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //遍历二级分类list集合
            for (int m = 0; m < twoSubjectList.size(); m++) {
                //获取每个二级分类
                EduSubject tSubject = twoSubjectList.get(m);
                //判断二级分类parentid和一级分类id是否一样
                if(tSubject.getParentId().equals(eduSubject.getId())){
                    //把tSubject值复制到TwoSubject里面，放到twoFinalSubject里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            //把一级下面所有的二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);
        }


        return finalSubjectList;
    }





}




