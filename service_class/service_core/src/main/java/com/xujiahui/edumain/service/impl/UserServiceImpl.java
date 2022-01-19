package com.xujiahui.edumain.service.impl;

import com.xujiahui.edumain.entity.User;
import com.xujiahui.edumain.mapper.UserMapper;
import com.xujiahui.edumain.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
