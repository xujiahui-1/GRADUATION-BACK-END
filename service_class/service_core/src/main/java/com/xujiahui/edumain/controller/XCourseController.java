package com.xujiahui.edumain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujiahui.commonutils.X;
import com.xujiahui.edumain.entity.XCourse;
import com.xujiahui.edumain.entity.vo.CoursePublishVo;
import com.xujiahui.edumain.entity.vo.CourseQuery;
import com.xujiahui.edumain.service.XCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 授業
 * </p>
 *
 * @author xujiahui
 * @since 2021-11-17
 */
@Api(tags = "授業API")
@RestController
@RequestMapping("/servicemain/x-course")
public class XCourseController {

    @Autowired
     private XCourseService xCourseService;
    /**
     * course 追加
     */
    @ApiOperation("course追加")
    @PostMapping("/addCourseInfo")
    public X addCourseInfo(@RequestBody XCourse xCourse){
        String id= xCourseService.saveCourseInfo(xCourse);
        return X.ok().data("courseId",id);
    }
    /**
     *　courseId よって　course情報検索
     */
    @ApiOperation("courseIdよってcourse情報検索")
    @GetMapping("/getCourseInfoById/{courseId}")
    public X getCourseInfoById(@PathVariable String courseId){
      XCourse xCourse=  xCourseService.getCourseById(courseId);
      return X.ok().data("courseInfo",xCourse);
    }
    /**
     * course情報 update
     */
    @ApiOperation("course情報更新")
    @PostMapping("/updateCourseInfo")
    public  X updateCourseInfo(@RequestBody XCourse xCourse){
        xCourseService.updateCourseInfo(xCourse);
        return X.ok();
    }

    /**
     * 根据课程id查询最终发布中所有信息
     */
    @ApiOperation("course追加最後の情報確認")
    @GetMapping("/getPublishCourseInfo/{id}")
    public X getPublishCourseInfo(@PathVariable String id ){
        CoursePublishVo coursePublishVo= xCourseService.publishCourseInfo(id);
        return  X.ok().data("publishCourse",coursePublishVo);
    }
    /**
     * 课程最终发布之修改studes字段
     */
    @PostMapping("/publishCourse/{id}")
    public  X publishCourse(@PathVariable String id){
        XCourse eduCourse=new XCourse();
        eduCourse.setId(id);
        boolean aaa=xCourseService.updateById(eduCourse);
        System.out.println(aaa);
        return X.ok().success(aaa);
    }
    /**
     * 查询所有课程信息
     */
    @ApiOperation("すべてのcourse情報")
    @PostMapping("/getAllCourseInfo/{current}/{limit}")
    public X getAllCourseInfo(@PathVariable Long current,
                                @PathVariable Long limit,
                              @RequestBody(required = false) CourseQuery courseQuery){

        Page<XCourse> page=new Page<>(current,limit);
        QueryWrapper<XCourse> queryWrapper=new QueryWrapper();
        String name = courseQuery.getName();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();
        if(!StringUtils.isEmpty(name)){//spring 包中的
            //构建他的条件
            queryWrapper.like("name",name); //字段名，传过来的值
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin); //大于等于
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end); //小于等于
        }
        Page<XCourse> resultPage = xCourseService.page(page, queryWrapper);
        long total = page.getTotal();
        List<XCourse> records = page.getRecords();
        return X.ok().data("total",total).data("rows",records);
    }
    /**
     * 删除课程
     */
    @ApiOperation("courseを削除")
    @DeleteMapping("/deleteCourseById/{courseId}")
    public X deleteCourseById(@PathVariable  String courseId ){
        xCourseService.deleteById(courseId);
        return X.ok();
    }
    /**
     * select All course by teacherId
     */
    @ApiOperation("teacherIdによって、courseを選択")
    @GetMapping("selectAllCourseByTeacherId/{teacherId}")
    public X selectAllCourseByTeacherId(@PathVariable String teacherId){
        QueryWrapper<XCourse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherId);
        List<XCourse> courseList = xCourseService.list(queryWrapper);
        return  X.ok().data("courseList",courseList );

    }


}

