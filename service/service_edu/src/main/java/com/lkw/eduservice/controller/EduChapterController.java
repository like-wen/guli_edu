package com.lkw.eduservice.controller;

import com.lkw.commonutils.R;
import com.lkw.eduservice.entity.EduChapter;
import com.lkw.eduservice.entity.chapter.ChapterVo;
import com.lkw.eduservice.service.EduChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/chapter")
@Slf4j
public class EduChapterController {


    @Autowired
    private EduChapterService chapterService;


    //根据课程id查询章节小结
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list=chapterService.getChapterVideoByCourseId(courseId);
        log.info("test");
        return R.ok().data("allChapterVideo",list);

    }

    //添加课程章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return R.ok();
    }

    //查询根据id课程章节
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter chapterById = chapterService.getById(chapterId);
        return R.ok().data("chapter", chapterById);
    }


    //修改课程章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return R.ok();
    }

    //删除课程章节
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean result = chapterService.deleteChapterById(chapterId);
        if (result) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}
