package com.xujiahui.edumain.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 用于课程最终发布的数据封装对应
 */
@ApiModel(value = "用于课程最终发布的数据封装对应")
@Data
public class CoursePublishVo {

    private static  final long serialVersionUid=1L;

    private String title;

    private String cover;

    private Integer lessonNum;

    private String teacherName;


}
