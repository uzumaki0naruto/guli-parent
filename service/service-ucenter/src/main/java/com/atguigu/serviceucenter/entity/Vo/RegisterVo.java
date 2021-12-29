package com.atguigu.serviceucenter.entity.Vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVo {

    private String nickname;
    private String password;
    private String email;
    private String code;
}
