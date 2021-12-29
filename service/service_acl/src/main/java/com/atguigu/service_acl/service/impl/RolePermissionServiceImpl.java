package com.atguigu.service_acl.service.impl;

import com.atguigu.service_acl.entity.RolePermission;
import com.atguigu.service_acl.mapper.RolePermissionMapper;
import com.atguigu.service_acl.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}
