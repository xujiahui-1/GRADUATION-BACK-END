package com.xujiahui.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xujiahui.login.entity.StudentMember;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author test.java
 * @since 2021-07-13
 */
public interface UcenterMemberService extends IService<StudentMember> {

    String loginUser(StudentMember ucenterMember);

    void registerUser(StudentMember ucenterMember);

    int getRegisterNumByDay(String day);
}
