package com.zel.market.app.request;

import com.zel.market.common.Response;
import com.zel.market.common.enumcom.EResponseCode;
import com.zel.market.dto.AuthCallback;
import com.zel.market.dto.AuthToken;
import com.zel.market.exception.AuthException;

/**
 * JustAuth {@code Request}公共接口，所有平台的{@code Request}都需要实现该接口
 * <p>
 * {@link AuthRequest#authorize()}
 * {@link AuthRequest#authorize(String)}
 * {@link AuthRequest#login(AuthCallback)}
 * {@link AuthRequest#revoke(AuthToken)}
 * {@link AuthRequest#refresh(AuthToken)}
 *
 */
public interface AuthRequest {

    /**
     * 返回授权url，可自行跳转页面
     * <p>
     * 不建议使用该方式获取授权地址，不带{@code state}的授权地址，容易受到csrf攻击。
     * 建议使用{@link AuthDefaultRequest#authorize(String)}方法生成授权地址，在回调方法中对{@code state}进行校验
     *
     * @return 返回授权地址
     */
    @Deprecated
    default String authorize() {
        throw new AuthException(EResponseCode.NOT_IMPLEMENTED);
    }


    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     */
    default String authorize(String state) {
        throw new AuthException(EResponseCode.NOT_IMPLEMENTED);
    }

    /**
     * 第三方登录
     *
     * @param authCallback 用于接收回调参数的实体
     * @return 返回登录成功后的用户信息
     */
    default Response login(AuthCallback authCallback) {
        throw new AuthException(EResponseCode.NOT_IMPLEMENTED);
    }

    /**
     * 撤销授权
     *
     * @param authToken 登录成功后返回的Token信息
     * @return AuthResponse
     */
    default Response revoke(AuthToken authToken) {
        throw new AuthException(EResponseCode.NOT_IMPLEMENTED);
    }

    /**
     * 刷新access token （续期）
     *
     * @param authToken 登录成功后返回的Token信息
     * @return AuthResponse
     */
    default Response refresh(AuthToken authToken) {
        throw new AuthException(EResponseCode.NOT_IMPLEMENTED);
    }
}
