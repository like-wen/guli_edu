package com.lkw.eduservice.service;

        import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
        import com.lkw.eduservice.entity.EduChapter;
        import com.baomidou.mybatisplus.extension.service.IService;
        import com.lkw.eduservice.entity.EduVideo;
        import com.lkw.eduservice.entity.chapter.ChapterVo;
        import com.lkw.servicebase.exceptionhandler.GuliException;

        import java.util.List;

/**
 * @author 李可文
 * @description 针对表【edu_chapter(课程)】的数据库操作Service
 * @createDate 2022-10-07 16:40:12
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapterById(String chapterId);

    void removeChapterByCourseId(String courseId) ;
}
