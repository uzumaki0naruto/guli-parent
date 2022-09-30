package com.atguigu.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class jwtUtils {

    public static final long EXPIRE = 1000 * 60 * 60 * 24;  //token过期时间
    public static final String APP_SECRET =
            "ukc8BDbRigUDaY6pZFfWus2jZWLPHO"; //密钥:密码加密方式,自定义

    public static String getJwtToken(String id, String nickname){



        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")  //设置token头信息
                .setHeaderParam("alg", "HS256") //设置token头信息

                .setSubject("guli-user") //设置主题，也就是jwt所有者
                .setIssuedAt(new Date())//设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//设置过期时间

                .claim("id", id)   //自定义私有字段
                .claim("nickname", nickname) //自定义私有字段
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)  //token生成规则
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @param request
      */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        System.out.println("Token:"+jwtToken);
        if(StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }


}
