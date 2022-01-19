package com.xujiahui.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xujiahui.commonutils.JwtUtils;
import com.xujiahui.commonutils.X;
import com.xujiahui.login.entity.StudentMember;
import com.xujiahui.login.service.UcenterMemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/servicelogin/ucenter-member")
public class UcenterMemberController {

    @Autowired
    UcenterMemberService ucenterMemberService;

    /*
        登录方法
     */
    @PostMapping("/login")
    public X loginUser(@RequestBody StudentMember ucenterMember){

        String token =ucenterMemberService.loginUser(ucenterMember);

        return  X.ok().data("token",token);
    }


    /**
     * 注册方法
     */
    @PostMapping("/register")
    public X register(@RequestBody StudentMember ucenterMember){

       ucenterMemberService.registerUser(ucenterMember);
        System.out.println("操");
        return  X.ok();
    }
/*+
    根据Token获取用户信息
 */
    @GetMapping("getMemberInfo")
        public  X getMemberInfo(HttpServletRequest request){
        //调用jwt工具类方法，根据request对象获取头信息，返回用户id
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println("memberIdByJwtToken*******" +memberIdByJwtToken);
        //查询数据库根据用户id获取用户信息
        StudentMember member = ucenterMemberService.getById(memberIdByJwtToken);

        return X.ok().data("userInfo",member);
    }

    /**
     * 根据用户名获取用户信息
     */
    @GetMapping("getMemberInfoName/{nickname}")
    public  X getMemberInfo(@PathVariable String nickname){
    QueryWrapper<StudentMember> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("nickname",nickname);
    System.out.println("---------"+nickname);
        StudentMember one = ucenterMemberService.getOne(queryWrapper);
        System.out.println("*********"+one);
        return X.ok().data("userInfo",one);
    }

    /**
     * select registerNum by day
     *
     */
    @GetMapping("/getRegisterNumByDay/{day}")
    public X getRegisterNumByDay(@PathVariable String day){
       int registerNum= ucenterMemberService.getRegisterNumByDay(day);
       return X.ok().data("registerNum",registerNum);
    }
}

