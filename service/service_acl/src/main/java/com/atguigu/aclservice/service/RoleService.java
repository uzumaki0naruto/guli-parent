package com.atguigu.aclservice.service;

import com.atguigu.aclservice.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface RoleService extends IService<Role> {

    void saveUserRoleRealtionShip(String userId, String[] roleId);

    Map<String, Object> findRoleByUserId(String userId);
}
