package com.atguigu.edu.controller;

import com.atguigu.commontuils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EduLoginController {
    @PostMapping("/eduservice/user/login")
    public R login(){
        return R.ok().data("token","admin");
    }



    @PostMapping("/eduservice/user/info")
    public R getInfo(){
        return R.ok().data("roles","admin").data("name","admin");
//                .data("avatar","");
    }

}
