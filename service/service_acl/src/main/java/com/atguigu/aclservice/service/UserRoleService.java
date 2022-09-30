package com.atguigu.aclservice.service;

import com.atguigu.aclservice.entity.Role;
import com.atguigu.aclservice.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public interface UserRoleService extends IService<UserRole> {

    List<Role> getRoleByUserId(String id);
}
