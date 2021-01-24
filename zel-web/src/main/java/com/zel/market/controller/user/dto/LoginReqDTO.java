package com.zel.market.controller.user.dto;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 登录参数
 * Validator
 *
 * @NotBlank 字符串不能为null, 字符串trim()后也不能等于“”
 *
 * @NotEmpty 不能为null，集合、数组、map等size()不能为0；字符串trim()后可以等于“”
 *
 * @Length 长度必须在指定范围内
 *
 * @Email 必须是email格式
 */
@Getter
@Setter
public class LoginReqDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 4, message = "密码必须大于等于4位")
    private String password;

    @Email(message = "必须是email格式")
    private String email;
}
