package com.atguigu.serviceucenter.service;

import com.atguigu.serviceucenter.entity.UcenterMember;
import com.atguigu.serviceucenter.entity.Vo.LoginInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-11-25
 */

public interface UcenterMemberService extends IService<UcenterMember> {

    void regist(String nickname,String password,String email,String code);

    String sendMail(String email);

    String login(UcenterMember member);

    LoginInfoVo getLoginInfo(String memberId);

    UcenterMember getOpenIdMember(String openid);

    int countRegister(String day);
}
