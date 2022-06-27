package com.atguigu.aclservice.service.impl;

import com.atguigu.aclservice.entity.Role;
import com.atguigu.aclservice.entity.UserRole;
import com.atguigu.aclservice.mapper.RoleMapper;
import com.atguigu.aclservice.service.RoleService;
import com.atguigu.aclservice.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    UserRoleService userRoleService;

    @Override
    public void saveUserRoleRealtionShip(String userId, String[] roleId) {
        UserRole userRole = new UserRole();

        for (String s : roleId) {
            userRole.setUserId(userId);
            userRole.setRoleId(s);

            userRoleService.save(userRole);

        }


    }

    @Override
    public Map<String, Object> findRoleByUserId(String userId) {
        List<Role> roles= userRoleService.getRoleByUserId(userId);
        Map<String, Object> map = roles.stream().collect(Collectors.toMap(Role::getId, p -> p));
        return map;
    }
}
