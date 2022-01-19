package com.xujiahui.edumain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujiahui.commonutils.X;
import com.xujiahui.edumain.entity.XTeacher;
import com.xujiahui.edumain.entity.vo.TeacherQuery;
import com.xujiahui.edumain.service.XTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 先生 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Api(tags = "教員API")
@RestController
@RequestMapping("/servicemain/x-teacher")
public class XTeacherController {
    @Autowired
    XTeacherService xTeacherServiceImpl;
    /**
     * get All teacher
     */
    @ApiOperation("すべての教員情報")
    @GetMapping("/getAllTeacher")
    public X getAllTeacher (){
    List<XTeacher> teacherList=   xTeacherServiceImpl.getAllTeacher();
        return X.ok().data("items",teacherList);
    }

    /**
     * delete teacher ById
     */
    @ApiOperation("教員削除")
    @DeleteMapping("/deleteTeacherById/{teacherId}")
    public X deleteTeacherById(@PathVariable String teacherId){
        xTeacherServiceImpl.deleteTeacherById(teacherId);
        return  X.ok();
    }
    /**
     *select teacher page
     */
    @ApiOperation("教員情報をページ分け")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public X pageTeacher(@PathVariable Long current,
                          @PathVariable Long limit){
        Page<XTeacher> page=new Page<>(current,limit);
        Page<XTeacher> p = xTeacherServiceImpl.page(page);
        long total = page.getTotal(); //総数
        List<XTeacher> records = page.getRecords();//page
        return X.ok().data("total",total).data("rows",records);
    }
    /**
     *select teacher page and 条件
     */
    @ApiOperation("条件によって、教員情報を検索")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public X pageTeacherCondition(@PathVariable Long current ,
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<XTeacher> page=new Page<>(current,limit);
        QueryWrapper<XTeacher>queryWrapper=new QueryWrapper();
        //多条件组合查询，比较类似动态sql
        //判断条件值是否为空，不为空，则拼接
        String name = teacherQuery.getName();

        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
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
        Page<XTeacher> resultPage = xTeacherServiceImpl.page(page, queryWrapper);
        long total = page.getTotal();
        List<XTeacher> records = page.getRecords();
        return X.ok().data("total",total).data("rows",records);
    }
    /**
     * add teacher
     */
    @ApiOperation("教員を追加")
    @PostMapping("/addTeacher")
    public X addTeacher(@RequestBody XTeacher xTeacher){
        boolean save = xTeacherServiceImpl.save(xTeacher);
        if(save){
            return X.ok();
        }else{
            return X.error();
        }
    }
    /**
     * update teacher
     */
    @ApiOperation("教員情報更新")
    @PostMapping("/updateTeacher")
    public X updateTeacher(@RequestBody XTeacher xTeacher){

        boolean b = xTeacherServiceImpl.updateById(xTeacher);
        if(b){
            return  X.ok();
        }else {
            return X.error();
        }
    }
    /**
     * get teacher ById
     */
    @ApiOperation("teacherIdによって、教員を選択")
    @GetMapping("/getTeacherById/{teacherId}")
    public X getTeacherById(@PathVariable String teacherId){
        XTeacher t = xTeacherServiceImpl.getById(teacherId);
        if(t!=null){
            return X.ok().data("teacher",t);
        }else {
            return  X.error().message("この先生がないです");
        }
    }
}

