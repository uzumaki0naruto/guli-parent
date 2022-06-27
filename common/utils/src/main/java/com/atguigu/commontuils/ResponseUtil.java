package com.atguigu.commontuils;


//import com.atguigu.commontuils.R;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import javax.servlet.http.HttpServletResponse;

//public class ResponseUtil {
//
//    public static void out(HttpServletResponse response, R r) {
//        //把保存数据序列化为json数据
//        ObjectMapper mapper = new ObjectMapper();
//        response.setStatus(HttpStatus.OK.value());
////        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//        //设置content-Type
//        response.setContentType("application/json;charset=utf-8");
//        try {
////            返回给前端
////            mapper.writeValue(response.getWriter(), r);
//            mapper.writeValue(response.getOutputStream(),r);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


import com.atguigu.commontuils.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static void out(HttpServletResponse response, R r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}