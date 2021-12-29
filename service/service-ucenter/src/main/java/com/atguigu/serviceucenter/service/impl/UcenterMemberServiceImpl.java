package com.atguigu.serviceucenter.service.impl;

import com.atguigu.MD5.MD5;
import com.atguigu.Mail.MailUtils;
import com.atguigu.exceptionhandler.guliException;
import com.atguigu.jwt.jwtUtils;
import com.atguigu.serviceucenter.entity.UcenterMember;
import com.atguigu.serviceucenter.entity.Vo.LoginInfoVo;
import com.atguigu.serviceucenter.mapper.UcenterMemberMapper;
import com.atguigu.serviceucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-11-25
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    /*
    登入
     */
    @Override
    public String login(UcenterMember member) {
        String email = member.getEmail();
        String password = member.getPassword();
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            throw new guliException(20001,"登入失败-");
        }
//        查询email是否存在
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        UcenterMember member1 = this.getOne(queryWrapper);
        if(member1 == null){
            throw new guliException(20001,"手机号不存在");
        }

        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5

        if(!MD5.encrypt(password).equals(member1.getPassword())) {
            throw new guliException(20001,"密码错误登录失败");
        }

//        判断用户是否禁用
        if(member1.getIsDeleted()){
            throw new guliException(20001,"账号有问题,登入失败.");
        }
        String jwtToken = jwtUtils.getJwtToken(member1.getId(), member.getNickname());


        return jwtToken;
    }

    /*
根据id获取信息
  */
        @Override
        public LoginInfoVo getLoginInfo(String memberId) {

            UcenterMember member = this.getById(memberId);
            LoginInfoVo loginInfoVo = new LoginInfoVo();

            return null;
        }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> ucenterMemberQueryWrapper = new QueryWrapper<>();
        ucenterMemberQueryWrapper.eq("openid",openid);
        UcenterMember member = this.getOne(ucenterMemberQueryWrapper);
        return member;
    }

    @Override
    public int countRegister(String day) {

        return baseMapper.countRegister(day);
    }


    /*
    注册
    */
    @Override
    public void regist(String nickname,String password,String email,String code) {


            UcenterMember ucenterMember = new UcenterMember();
            ucenterMember.setNickname(nickname);
//          ucenterMember.setPassword(password);
            ucenterMember.setPassword(MD5.encrypt(password));//密码需要加密的
            ucenterMember.setIsDisabled(false);//用户不禁用
            ucenterMember.setAvatar("https://easonchan.oss-cn-beijing.aliyuncs.com/2021/11/21/aac776d7a65a4a8ba9e22975bba87366touxiang.png");
            ucenterMember.setEmail(email);
            boolean save = this.save(ucenterMember);
            if (save){
                System.out.println("注册成功");
            }else{
                System.out.println("注册失败");
            }
    }

    /*
     发邮件
      */
    @Override
    public String sendMail(String email) {
        String code = MailUtils.sendMail(email);
        return code;
    }
}
