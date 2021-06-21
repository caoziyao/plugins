package com.zel.market.app.service.login.db;

//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
//@TableName("tb_sso")
public class UserThird implements Serializable {

    //@TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键id

    private Long userId;

    private String appName; //第三方应用名称

    private String accessToken; //授权token

    private String refreshToken; //刷新token

    private Long expiresIn; //授权token有效期

    private Long reExpiresIn; //刷新token有效期

    private Long expiresInR2; //r2级别有效期

    private Long expiresInR1; //r1级别有效期

    private Long expiresInW2; //w2级别有效期

    private Long expiresInW1; //w1级别有效期

    private String tokenType; //token类别

    private String appUserId; //应用用户id

    private String appUserNick; //应用用户昵称

    private Date lastUpdate; //最后更新时间

    private Long authorizeTime; //授权时间

    private String scope; //以空格分隔的权限列表

    private int status; //授权帐号状态 0：不可用 1：可用

    private int isAutoImport; //是否启用后台异步导入数据 0：否 1：是

    private String ext; //扩展字段
}
