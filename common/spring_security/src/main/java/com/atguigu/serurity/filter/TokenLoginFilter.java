package com.atguigu.serurity.filter;

import com.atguigu.commontuils.R;
import com.atguigu.commontuils.ResponseUtil;

import com.atguigu.serurity.entity.SecurityUser;
import com.atguigu.serurity.entity.User;
import com.atguigu.serurity.security.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Slf4j
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

//    @Autowired
    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login","POST"));
        log.info("login过滤器初始化");
    }

//    1. attemptAuthentication(request, response)
//
//这是 AbstractAuthenticationProcessingFilter
// 中的一个抽象方法，包含登录主逻辑，由其子类实现具体的登录验证，
// 如 UsernamePasswordAuthenticationFilter 是使用表单方式登录的具体实现。
// 如果是非表单登录的方式，如JNDI等其他方式登录的可以通过继承 AbstractAuthenticationProcessingFilter 自定义登录实现。
// UsernamePasswordAuthenticationFilter 的登录实现逻辑如下。
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {

            log.info("请求路径是"+req.getRequestURI());
            // 此过滤器的用户名密码默认从request.getParameter()获取，但是这种
            // 读取方式不能读取到如 application/json 等 post 请求数据，需要把
            // 用户名密码的读取逻辑修改为到流中读取request.getInputStream()
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
//密码用来给userdetailsServer认证 ,password用来给 DefaltPasswordEncoder 进行密码匹配，会根据username得到数据库里加密了的密码,然后用这里的password进行匹配
            return authenticationManager.authenticate( //authenticate为验证方法
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 登录成功
     * @param req
     * @param res
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername(), user.getPermissionValueList());
        log.info("认证成功,用户名："+user.getUsername()+",密码:"+user.getPassword()+",权限列表"+user.getPermissionValueList());
        ResponseUtil.out(res, R.ok().data("token", token));
    }

    /**
     * 登录失败
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败");
        ResponseUtil.out(response, R.error());
    }
}
