package com.atguigu.aclservice.service.impl;

import com.atguigu.aclservice.entity.Role;
import com.atguigu.aclservice.entity.UserRole;
import com.atguigu.aclservice.mapper.UserRoleMapper;
import com.atguigu.aclservice.service.RoleService;
import com.atguigu.aclservice.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    RoleService roleService;




    @Override
    public List<Role> getRoleByUserId(String id) {
        ArrayList<Role> result = new ArrayList<>();
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        QueryWrapper<UserRole> wrapper = userRoleQueryWrapper.eq("user_id",id);
        List<UserRole> userRoleList = this.list(wrapper);
        for (UserRole userRole : userRoleList) {

            Role role = roleService.getById(userRole.getRoleId());
            result.add(role);

        }


        return result;
    }
}
