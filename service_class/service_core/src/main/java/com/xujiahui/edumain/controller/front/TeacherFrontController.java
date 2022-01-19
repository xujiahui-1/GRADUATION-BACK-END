package com.xujiahui.edumain.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xujiahui.commonutils.X;
import com.xujiahui.edumain.entity.XTeacher;
import com.xujiahui.edumain.service.XTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Api(tags = "教員ページ用API")
@RestController
@RequestMapping("/servicemain/teacherFront")
public class TeacherFrontController {

    @Autowired
    private XTeacherService xTeacherService;

//    分页查询讲师的方法
    @ApiOperation("教員情報のページ分け表示")
    @PostMapping("/getTeacherFrontList/{page}/{limit}")
        public X getTeacherFrontList(@PathVariable long page, @PathVariable long limit ){
            Page<XTeacher> pageTeacher =new Page<>(page,limit);
            Map<String,Object > map= xTeacherService.getTeacherFrontList(pageTeacher);
            return X.ok().data(map);

        }
}