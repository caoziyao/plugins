package com.zel.market.app.request;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Scope 工具类，提供对 scope 类的统一操作
 *
 */
public class AuthScopeUtils {

    /**
     * 获取 {@link me.zhyd.oauth.enums.scope.AuthScope} 数组中所有的被标记为 {@code default} 的 scope
     *
     * @param scopes scopes
     * @return List
     */
    public static List<String> getDefaultScopes(AuthScope[] scopes) {
        if (null == scopes || scopes.length == 0) {
            return null;
        }
        return Arrays.stream(scopes)
                .filter((AuthScope::isDefault))
                .map(AuthScope::getScope)
                .collect(Collectors.toList());
    }

    /**
     * 从 {@link me.zhyd.oauth.enums.scope.AuthScope} 数组中获取实际的 scope 字符串
     *
     * @param scopes 可变参数，支持传任意 {@link me.zhyd.oauth.enums.scope.AuthScope}
     * @return List
     */
    public static List<String> getScopes(AuthScope... scopes) {
        if (null == scopes || scopes.length == 0) {
            return null;
        }
        return Arrays.stream(scopes).map(AuthScope::getScope).collect(Collectors.toList());
    }
}
