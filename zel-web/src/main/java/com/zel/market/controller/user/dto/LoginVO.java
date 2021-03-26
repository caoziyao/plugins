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
public class LoginVO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 4, message = "密码必须大于等于4位")
    private String password;

    @Email(message = "必须是email格式")
    private String email;

    //密码登录、验证码登录
    private String loginType;

    //小程序平台
    private String platform;

    private String accesstoken;

    //邀请码
    private String inviteCode;

    //邀请人token
    private String inviteToken;

    //来源
    private String source;

    //短信验证码
    private String msgCode;

    //三方名称 QQ、WEIXIN（三方登录必传）
    private String thirdName;

    private Long accessToken;

    private String ipAddr;
    private String userGgent;
    private String device;

    //客户端标志 PC:主站，ANDROID：安卓，IOS：ios，XCX：小程序
    private String clientCode;

    private long userId;
    private String unionId;
    private String identifyType;
    /**
     * 第三方授权token
     */
    private String thirdAccessToken;
    /**
     * 第三方刷新token
     */
    private String thirdRefreshToken;
    /**
     * 第三方授权token有效期
     */
    private long expiresIn;
    /**
     * 应用用户昵称
     */
    private String appUserNick;
    /**
     * 授权时间
     */
    private long authorizeTime;
    /**
     * 手机号码
     */
    private String mobile;

    private String appUserId;

    private String openId;
    private String name;
    private String nickName;
    private String avatar;

}
