package com.xujiahui.edumain.controller;

import com.xujiahui.commonutils.X;
import com.xujiahui.edumain.service.XTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 处理登录请求(暂时，之后会用spring security直接处理)
 *  其中这些数据的键，都是依据前端模板规定好的名进行匹配的，
 */
@Api(tags = "管理者登録API ")
@RestController

@RequestMapping(value = "/servicemain/user")
public class XLoginController {
    @Autowired
    private XTeacherService xTeacherService;

    //login
    @PostMapping("/login")
    public X login(){
        return  X.ok().data("token","admin");
    }
    //info
    @GetMapping("/info")
    public X info(){
        return  X.ok().data("roles","[admin]").data("name","admin")
                .data("avatar","https://pic1.zhimg.com/v2-6939fc1d0b4a6a8c11b870ede939d113_1440w.jpg?source=172ae18b");
    }
}
