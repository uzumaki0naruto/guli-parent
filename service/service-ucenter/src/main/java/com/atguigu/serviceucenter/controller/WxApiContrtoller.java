package com.atguigu.serviceucenter.controller;

import com.atguigu.exceptionhandler.guliException;
import com.atguigu.jwt.jwtUtils;
import com.atguigu.serviceucenter.entity.UcenterMember;
import com.atguigu.serviceucenter.entity.WXUtils;
import com.atguigu.serviceucenter.service.UcenterMemberService;
import com.atguigu.serviceucenter.utils.HttpClientUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiContrtoller {

    @Autowired
    private UcenterMemberService ucenterMemberService;

//    code微信给的凭证,state前面自定义的
    @GetMapping("/callback")
    public String callback(String code,String state){
        try {
            String result="";
            System.out.println("code:"+code+"state:"+state);
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    WXUtils.APPID,
                    WXUtils.APPSECRET,
                    code
            );
            System.out.println("accessTokenTrl:"+accessTokenUrl);
            // 使用HttpClient发送请求,返回的json的字符串,字符串分割不方便,分割成map集合
            String   accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            System.out.println("accessTokenInfo:"+accessTokenInfo);
            Gson gson = new Gson();
            Map mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String openid = (String) mapAccessToken.get("openid");
            String access_token = (String) mapAccessToken.get("access_token");
            System.out.println("openid:"+openid+"....access_token:"+access_token);
            UcenterMember member = ucenterMemberService.getOpenIdMember(openid);
            if(member == null){
                String baseUserInfoUrl="https://api.weixin.qq.com/sns/userinfo"+
                        "?access_token=%s"+
                        "&openid=%s";
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                String userInfo = HttpClientUtils.get(userInfoUrl);
                Map userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String)userInfoMap.get("nickname");//昵称
                String headimgurl = (String)userInfoMap.get("headimgurl");//头像

                member = new UcenterMember();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                ucenterMemberService.save(member);
            }
            String jwtToken = jwtUtils.getJwtToken(member.getId(), member.getNickname());

            return "redirect:http://localhost:3000?token="+jwtToken;
        } catch (Exception e) {
            throw new guliException(20001,"登录失败-");
        }

    }

    @GetMapping("/wx")
    public String wx(){
        System.out.println("......"+WXUtils.APPID+">>>"+WXUtils.REDIRECTURL);
        String baseUrl= "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        String redirectUrl=WXUtils.REDIRECTURL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        }catch(Exception e) {
        }
        String url=String.format(
                baseUrl,
                WXUtils.APPID,
                redirectUrl,
                "atguigu"
        );
        return "redirect:"+url;
    }

}
