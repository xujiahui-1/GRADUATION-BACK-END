package com.xujiahui.edumain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujiahui.edumain.entity.XCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xujiahui.edumain.entity.vo.CourseFrontVo;
import com.xujiahui.edumain.entity.vo.CoursePublishVo;
import com.xujiahui.edumain.entity.vo.CourseWebVo;

import java.util.Map;

/**
 * <p>
 * 授業 服务类
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
public interface XCourseService extends IService<XCourse> {

    /**
     courseIdによって、chapterを削除する
     */
    String saveCourseInfo(XCourse xCourse);

    XCourse getCourseById(String courseId);

    void updateCourseInfo(XCourse xCourse);

    void deleteById(String courseId);

    CoursePublishVo publishCourseInfo(String id);

    Map<String, Object> getCourseList(Page<XCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getFrontCourseInfo(String courseId);

    void courseViewUpdate(String courseId);
}
