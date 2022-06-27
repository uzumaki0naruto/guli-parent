package com.atguigu.aclservice.controller;


import com.atguigu.aclservice.service.IndexService;
import com.atguigu.commontuils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl")
@Slf4j
public class index {

//    @Autowired
//    IndexService indexService;
//
//    @GetMapping("/g555")
//    public R getUserInfo(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//        Map<String, Object> userInfo = indexService.getUserInfo(name);
//        return R.ok().data("userInfo",userInfo);
//    }
//
//    @GetMapping("/getMenu45564")
//    public R getMenu(){
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Collection<? extends GrantedAuthority> authorities =
//                authentication.getAuthorities();
//        return R.ok().data("menuList",authentication);
//    }

    //
    @PostMapping("/login")
    public R getMenu(){
        log.info("自定义login");

        return R.ok();
    }









}
