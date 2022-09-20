package com.lkw.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lkw.commonutils.R;
import com.lkw.eduservice.entity.EduTeacher;
import com.lkw.eduservice.entity.vo.TeacherQuery;
import com.lkw.eduservice.service.EduTeacherService;
import com.lkw.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api(value = "讲师管理功能")//注解还有其他输出功能,暂时不写
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;
    //GetMapping,获取全部的教师列表

    //http://localhost:8001/eduservice/teacher/findAll
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        //调用service的list,查询条件为null(查到的所有都返回)
        List<EduTeacher> list=eduTeacherService.list(null);
        //int i=11/0;//模拟算术异常
        return R.ok().data("items",list);
    }

    //http://localhost:8001/eduservice/teacher/delete/1

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //{current}/{limit}======当前页/最多限制页数
    @ApiOperation(value = "教师列表分页" )
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current ,@PathVariable long limit){
/*

        //模拟的自定义异常
        try {
            int i=10/0;
        } catch (Exception e) {
            throw new GuliException(20001,"执行了自定义异常");
        }
*/



        //创建page对象
        Page<EduTeacher> eduTeacherPage=new Page<>(current,limit);
        //调用方法实现分页,会把得到的数据封装给eduTeacherPage,查询条件为null
        eduTeacherService.page(eduTeacherPage,null);
        //得到总记录数
        long total = eduTeacherPage.getTotal();
        //得到list数据集合
        List<EduTeacher> records = eduTeacherPage.getRecords();
        //返回的可以自己创建一个map集合,然后将两个对象合成一个对象,但是这里用的链式编程,两种结果都一样
        return R.ok().data("total",total).data("rows",records);
    }

    //4.条件查询分页方法
    @ApiOperation(value = "条件查询分页方法")


    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable Long current,
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建page
        Page<EduTeacher> pageCondition = new Page<>(current, limit);

        //QueryWrapper,构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询，动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件是否为空，拼接条件
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);//大于等于
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);//小于等于
        }
        wrapper.orderByDesc("gmt_create");

        //调用方法，实现分页查询
        eduTeacherService.page(pageCondition, wrapper);

        long total = pageCondition.getTotal();//获取总记录数
        List<EduTeacher> records = pageCondition.getRecords();//获取分页后的list集合
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }


    @ApiOperation("添加教师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        //可能缺一点数据判断
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else
            return R.error();
    }

    @ApiOperation("根据ID查询教师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher", eduTeacher);
    }

    @ApiOperation("修改教师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b) {
            return R.ok();
        } else
            return R.error();
    }
}

















