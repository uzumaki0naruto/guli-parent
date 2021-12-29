package com.atguigu.serviceucenter.entity;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WXUtils implements InitializingBean {
    @Value( "${wx.open.appid}")
    private String  appid;

    @Value("${wx.open.appsecret}")
    private String  appsecret;

    @Value("${wx.open.redirecturl}")
    private String  redirecturl;

    public static String APPID;

    public static String APPSECRET;

    public static String REDIRECTURL;

    @Override
    public void afterPropertiesSet() throws Exception {
        APPID=appid;
        APPSECRET=appsecret;
        REDIRECTURL=redirecturl;
    }
}
