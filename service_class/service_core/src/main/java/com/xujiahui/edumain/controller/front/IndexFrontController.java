package com.xujiahui.edumain.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.xujiahui.commonutils.X;
import com.xujiahui.edumain.entity.XCourse;
import com.xujiahui.edumain.entity.XTeacher;
import com.xujiahui.edumain.service.XCourseService;
import com.xujiahui.edumain.service.XTeacherService;
import org.springframework.cache.annotation.Cacheable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "ホームページ用API")
@RestController
@RequestMapping("/servicemain/indexfront")
public class IndexFrontController {
        @Autowired
    private XCourseService xCourseService;
    @Autowired
    private XTeacherService xTeacherService;

    /**
     * 查询前八条热门课程，查询前4个名师
     */
    @Cacheable(value = "data")
    @ApiOperation("ホームページで8の授業と4の教員情報を検索")
    @GetMapping("index")
    public X index(){
        /**
         * 查询前八条课程
         */
        QueryWrapper<XCourse> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 8");
        List<XCourse> courseList = xCourseService.list(queryWrapper);
        /**
         * 查询前4个师
         */
        QueryWrapper<XTeacher> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.orderByDesc("id");
        queryWrapper2.last("limit 4");
        List<XTeacher> teacherList = xTeacherService.list(queryWrapper2);


        return X.ok().data("eduList",courseList).data("teacherList",teacherList);
    }

}
