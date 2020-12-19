package com.zel.market.controller.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 登录参数
 * Validator
 *
 * @NotBlank 字符串不能为null, 字符串trim()后也不能等于“”
 *
 * @NotEmpty 不能为null，集合、数组、map等size()不能为0；字符串trim()后可以等于“”
 */
@Getter
@Setter
public class LoginReqDTO {

    @NotNull(message = "用户名不能为空")
    private String username;

//    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    private String password;
}
