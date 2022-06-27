package com.atguigu.aclservice.service.impl;

import com.atguigu.aclservice.entity.User;
import com.atguigu.aclservice.mapper.UserMapper;
import com.atguigu.aclservice.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        QueryWrapper<User> eq = userQueryWrapper.eq("username",username);
        User user = this.getOne(userQueryWrapper);


        return user;
    }
}
