package com.example.userservice.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */
@Data
public class LoginParam {

    @NotBlank(message = "用户名或邮箱不能为空")
    private String username;

    @NotBlank(message = "登录密码不能为空")
    private String password;

    private String authcode;
}
