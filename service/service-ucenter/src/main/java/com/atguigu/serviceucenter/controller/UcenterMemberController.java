package com.atguigu.serviceucenter.controller;


import com.atguigu.commontuils.R;
import com.atguigu.exceptionhandler.guliException;
import com.atguigu.jwt.jwtUtils;
import com.atguigu.orderVo.ucenterMemberVo;
import com.atguigu.serviceucenter.entity.UcenterMember;
import com.atguigu.serviceucenter.entity.Vo.LoginInfoVo;
import com.atguigu.serviceucenter.entity.Vo.RegisterVo;
import com.atguigu.serviceucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-11-25
 */
@RestController

@RequestMapping("/educenter/member")
public class UcenterMemberController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UcenterMemberService ucenterMemberService;

    /*
    用户登入功能
     */
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member)
    {
        System.out.println("password:"+member.getPassword());
        String token=ucenterMemberService.login(member);
        return R.ok().data("token",token);
    }





    /*
    用户注册功能
     */
    //Ucode为验证码
    @PostMapping("/regist")
    public R registUser(@RequestBody RegisterVo registerVo){

         String password=registerVo.getPassword();
         String code=registerVo.getCode();
         String email=registerVo.getEmail();
         String nickname=registerVo.getNickname();

        String key="key-"+email;
        String  ucode = (String) redisTemplate.opsForValue().get(key);

        if(password ==null || code ==null){
            System.out.println("表格不能有空");
            throw  new guliException(20001,"表格不能有空");
        }

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        int count = ucenterMemberService.count(wrapper);
        if(count > 0) {
            throw new guliException(20001,"注册失败");
        }

        if(!email.matches("^\\w+@(\\w+\\.)+\\w+$")){
            System.out.println("邮箱错误");
            throw new guliException(20001,"邮箱错误");
        }

        System.out.println("验证码ucode:"+ucode);
        System.out.println("code:"+code);
        if(ucode.equals(code)){
            System.out.println("允许注册");
            ucenterMemberService.regist(nickname,password,email,code);
        }else {
            System.out.println("注册失败");
        }
        return R.ok();
    }

    /*
    发送邮件功能
     */
    @GetMapping("/sendMail/{email}")
    public R sendMail(@PathVariable String email){
        System.out.println("发送邮件功能..."+email);
     String code= ucenterMemberService.sendMail(email);
     String key="key-"+email;
     if(redisTemplate.opsForValue().get(key)!=null){
         redisTemplate.opsForValue().set(key,code,60*2, TimeUnit.SECONDS);
     }else{
         redisTemplate.opsForValue().set(key,code,0);
     }
        return R.ok().data("验证码是",code);
    }


    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String memberId = jwtUtils.getMemberIdByJwtToken(request);
        System.out.println("memberid:"+memberId);
//        LoginInfoVo loginInfoVo =
//                ucenterMemberService.getLoginInfo(memberId);
        UcenterMember member = ucenterMemberService.getById(memberId);

        return R.ok().data("userInfo",member);

    }
//rpc使用
    @GetMapping("/getMemberInfo/{id}")
    public ucenterMemberVo getMemberInfoById(@PathVariable String id){
        UcenterMember member = ucenterMemberService.getById(id);
        ucenterMemberVo ucenterMemberVo = new ucenterMemberVo();
        BeanUtils.copyProperties(member,ucenterMemberVo);
        return ucenterMemberVo;
    }
//    查询某一天注册人数
    @GetMapping("countRegistet/{day}")
    public R countRegister(@PathVariable(value = "day") String day){


        int count = ucenterMemberService.countRegister(day);


        return R.ok().data("count",count);

    }


}

