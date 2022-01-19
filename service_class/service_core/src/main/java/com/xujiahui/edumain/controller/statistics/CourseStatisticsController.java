package com.xujiahui.edumain.controller.statistics;

import com.xujiahui.commonutils.X;
import com.xujiahui.edumain.entity.XCourse;
import com.xujiahui.edumain.service.XCourseService;
import com.xujiahui.edumain.service.XVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 統計class
 */
@Api(tags = "統計用API")
@RestController
@RequestMapping("/servicemain/CourseSta")
public class CourseStatisticsController {
    @Autowired
    private XVideoService xVideoService;

    @Autowired
    private XCourseService xCourseService;
    /**
     * 一日中すべての授業の総再生回数
     */
    @ApiOperation("一日中すべての授業の総再生回数")
    @GetMapping("/countvideoNum/{day}")
    public X countvideoNum(@PathVariable String day){
        int num= xVideoService.countvideoNum(day);
        return X.ok().data("countvideoNum",num);
    }
    /**
     * この授業の 阅覧量 更新　byId
     *
     */
    @ApiOperation("idによって、データを更新")
    @GetMapping("/courseViewUpdate/{courseId}")
    public X courseViewUpdate(@PathVariable String courseId){
        xCourseService.courseViewUpdate(courseId);
        return X.ok();
    }
}
