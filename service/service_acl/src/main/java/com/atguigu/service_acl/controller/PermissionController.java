package com.atguigu.service_acl.controller;


import com.atguigu.commontuils.R;
import com.atguigu.service_acl.entity.Permission;
import com.atguigu.service_acl.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PermissionController {


    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/getAllpermission")
    public R indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenuGuli();
        return R.ok().data("children",list);
    }

    @GetMapping("/remove/{id}")
    public R removeChildById(@PathVariable String id) {
        permissionService.removeChildById(id);
        return R.ok();
    }
}
