package com.atguigu.oss.controller;
import com.atguigu.commontuils.R;
import com.atguigu.oss.service.OssService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/eduoss")


public class OssController {

    @Autowired
     private OssService ossService;

    @PostMapping("/upload")
    public R uploadOssFile(MultipartFile file) throws IOException {
     String url=  ossService.uploadFileAvatar(file);
     return R.ok().data("url",url);
    }
}
