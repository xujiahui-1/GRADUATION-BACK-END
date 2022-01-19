package com.xujiahui.edumain.service;

import com.xujiahui.edumain.entity.XChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xujiahui.edumain.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 回数
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
public interface XChapterService extends IService<XChapter> {

    /*
        courseIdによって、chapterを削除する
     */
     void deleteChapterBycourseId(String courseId);

    List<XChapter> getAllchapterByCourseId(String courseId);

    String saveChapter(XChapter xChapter);

    List<ChapterVo> getChapterVideo(String courseId);
}
