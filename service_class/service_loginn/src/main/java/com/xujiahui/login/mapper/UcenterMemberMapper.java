package com.xujiahui.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujiahui.login.entity.StudentMember;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author test.java
 * @since 2021-07-13
 */
public interface UcenterMemberMapper extends BaseMapper<StudentMember> {

    int getRegisterNumByDay(String day);
}
