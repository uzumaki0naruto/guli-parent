package com.atguigu.security;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class secutiryConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //    首页所有人可以访问,功能页只有对应有权限的人才能访问
        http.authorizeRequests().antMatchers("http://localhost:9528/#/dashboard").permitAll();
//                .antMatchers("localhost:/level1/**").hasRole("vip1")
//                .antMatchers("/level2/**").hasRole("vip2")
//                .antMatchers("/level3/**").hasRole("vip3");
//        //    没有权限会默认到登入页,定制登入页
        http.formLogin();
//        //防止网站工具:get,post
//        http.csrf().disable();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        从内存中定义虚拟角色
//        密码编码:PasswordEncoder
//        再Spring Secutiry 5.0+ 新增了很多的加密方法
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("111")).roles("vip2","vip3")
//                .and()
//                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip3","vip2");
//
//

    }








}
