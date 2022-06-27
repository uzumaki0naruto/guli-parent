package com.atguigu.aclservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.aclservice.entity.Permission;
import com.atguigu.aclservice.entity.Role;
import com.atguigu.aclservice.entity.User;
import com.atguigu.aclservice.entity.UserRole;
import com.atguigu.aclservice.service.*;
import com.atguigu.exceptionhandler.guliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    PermissionService permissionService;


    @Override
    public Map<String, Object> getUserInfo(String username) {
        HashMap<String, Object> map = new HashMap<>();

        User user = userService.getUserByUsername(username);
        if(user==null  ){
            throw new guliException();
        }
        List<Role> roleList=  userRoleService.getRoleByUserId(user.getId());

        List<String> roleNameList = roleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());

       List<String>  permissionValue=permissionService.selectPermissionListByUserId(user.getId());
        map.put("name", user.getUsername());
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
       map.put("roles",roleNameList);
       map.put("permissionValueList",permissionValue);
        return map;
    }

    @Override
    public List<String> getMenu(String username) {
        User user = userService.getUserByUsername(username);
        //根据用户id获取用户菜单权限
        List<String> permissionList = permissionService.selectPermissionListByUserId(user.getId());
        return permissionList;
    }



}
