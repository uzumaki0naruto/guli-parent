package com.atguigu.serurity.security;


import com.atguigu.MD5.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * t密码的处理方法类型
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Component
@Slf4j
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    /**
     * @param strength
     *            the log rounds to use, between 4 and 31
     */
    public DefaultPasswordEncoder(int strength) {
    }

    public String encode(CharSequence rawPassword) {
        log.info("解密?"+rawPassword+"结果:"+MD5.encrypt(rawPassword.toString()));
        return MD5.encrypt(rawPassword.toString());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        log.info("进行密码加密");
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}