package com.atguigu.aclservice.service;

import com.atguigu.aclservice.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface PermissionService extends IService<Permission> {




    List<String> selectPermissionValueByUserId(String id);


    List<String> selectPermissionListByUserId(String id);

    void removeChildById(String id);
}
