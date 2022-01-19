package com.xujiahui.edumain.mapper;

import com.xujiahui.edumain.entity.XCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujiahui.edumain.entity.vo.CoursePublishVo;
import com.xujiahui.edumain.entity.vo.CourseWebVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 授業 Mapper 接口
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Mapper
public interface XCourseMapper extends BaseMapper<XCourse> {
    //根据课程id，查询所有表中相关信息
    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getFrontCourseInfo(String courseId);

    void courseViewUpdate(String courseId);
}
