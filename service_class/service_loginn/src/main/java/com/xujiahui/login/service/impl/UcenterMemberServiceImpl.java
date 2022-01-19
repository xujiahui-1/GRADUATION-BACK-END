package com.xujiahui.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xujiahui.commonutils.JwtUtils;
import com.xujiahui.commonutils.SHA256Utils;
import com.xujiahui.commonutils.XException;
import com.xujiahui.login.entity.StudentMember;

import com.xujiahui.login.mapper.UcenterMemberMapper;
import com.xujiahui.login.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-07-13
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, StudentMember> implements UcenterMemberService {

    @Autowired
    UcenterMemberMapper ucenterMemberMapper;
/*
    用户登录
 */
    @Override
    public String loginUser(StudentMember ucenterMember) {
        String nickName = ucenterMember.getNickname();
        String password = ucenterMember.getPassword();
        String id=ucenterMember.getId();
//        非空判断
        if(!StringUtils.hasLength(nickName) ||!StringUtils.hasLength(password)){
            throw  new XException(20001,"登录失败");
        }
//        判断id是否存在
        QueryWrapper <StudentMember> queryWrapper=new QueryWrapper();
        queryWrapper.eq("nickname",nickName);
//        queryWrapper.eq("password", SHA256Utils.SHA256(password));
        StudentMember one = baseMapper.selectOne(queryWrapper);

        if(one==null){
            throw  new XException(20001,"nickname不存在");
        }
//        判断密码
//        使用md5加密
        if(!SHA256Utils.SHA256(password).equals(one.getPassword())){

            throw  new XException(20001,"密码不正确");

        }
//        判断用户是否被禁映
        if(one.getIsDisabled()){
            throw  new XException(20001,"用户被禁用了");
        }
//        条件都通过，生成Token字符串，使用Jwt工具类
        String jwtToken = JwtUtils.getJwtToken(id, nickName);
        return jwtToken;
    }
/*
    用户注册功能
 */
    @Override
    public void registerUser(StudentMember ucenterMember) {
        String id = ucenterMember.getId();
        String password = ucenterMember.getPassword();
        String nickname = ucenterMember.getNickname();
//      判断这些值是否为空
        if(!StringUtils.hasLength(password)||!StringUtils.hasLength(nickname)) {
            throw new XException(20001,"用户id或密码或名称为空");
        }
//        判断Nickname是否存在
        QueryWrapper<StudentMember>  queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("nickname",nickname);
        StudentMember ucenterMember1 = baseMapper.selectOne(queryWrapper);
        if(ucenterMember1!=null){
            throw new XException(20001,"用户名已经被使用");
        }
//        MD5密码加密
        ucenterMember.setPassword(SHA256Utils.SHA256(password)) ;
         baseMapper.insert(ucenterMember);

    }
    /**
     * select registerNum by day
     *
     */
    @Override
    public int getRegisterNumByDay(String day) {
        return    ucenterMemberMapper.getRegisterNumByDay(day);
    }
}
