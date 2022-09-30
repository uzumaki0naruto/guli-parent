package com.atguigu.aclservice.controller;


import com.atguigu.commontuils.R;


import lombok.extern.slf4j.Slf4j;

import com.atguigu.aclservice.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
@Slf4j
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public R info(){
        log.info("获取登录用户用户名");
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo =
                indexService.getUserInfo(username);
        return R.ok().data(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    public R getMenu(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<String> permissionList = indexService.getMenu(username);
        log.info("权限列表2"+ permissionList);
        return R.ok().data("permissionList", permissionList);
//        {"path":"role/distribution/:id",
//                "component":"/acl/role/roleForm",
//                "hidden":true,
//                "meta":{"title":"角色权限"},
//                 "name":"name0_1195270621548568578"}
    }

    @PostMapping("logout")
    public R logout(){
        log.info("acl登出");
        return R.ok();
    }




}
