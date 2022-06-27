package com.atguigu.serurity.security;

import com.alibaba.fastjson.JSON;
import com.atguigu.commontuils.R;
import com.atguigu.commontuils.ResponseUtil;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * 未授权的统一处理方式
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Slf4j
@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.info("没有登入需要认证，如果是同步方法，需要返回html，如果是页面那就返回json");
        String header = request.getHeader("X-requested-with");
        if(header.equals("XMMHttpRequest")){
            log.info("是异步请求");
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("403还没有登录");
        }else{
            log.info("不是同步");
            ResponseUtil.out(response, R.error());
        }

    }
}
