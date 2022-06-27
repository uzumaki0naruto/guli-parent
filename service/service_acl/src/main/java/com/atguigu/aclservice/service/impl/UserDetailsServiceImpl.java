package com.atguigu.aclservice.service.impl;

import com.atguigu.aclservice.entity.Role;
import com.atguigu.aclservice.entity.User;
import com.atguigu.aclservice.service.IndexService;
import com.atguigu.aclservice.service.PermissionService;
import com.atguigu.aclservice.service.UserService;

import com.atguigu.serurity.entity.SecurityUser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Service("userDetailsService")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userdetailservice进行身份认证，返回一个securityUser");
        // 从数据库中取出用户信息
        User user = userService.getUserByUsername(username);
        // 判断用户是否存在
        if (null == user){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        com.atguigu.serurity.entity.User curUser = new com.atguigu.serurity.entity.User();
        BeanUtils.copyProperties(user,curUser);
        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;

    }
}
