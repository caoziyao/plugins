package com.example.api.aopdemo.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Objects;

/**
 * Description: 创建接口实现类代理对象，增强类的方法
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */
class UserDaoJDKInvocation implements InvocationHandler {

    private UserJDKDaoImpl obj;

    public UserDaoJDKInvocation(UserJDKDaoImpl obj) {
        this.obj = obj;
    }

    /**
     * 增强方法 + 原方法
     * 1, 把创建的是谁的代理对象，把谁传进来
     * 有参数构造传递
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 方法之前
        System.out.println("执行方法前.." + method.getName() + " 传递参数：" + Arrays.toString(args));
        // 被增强的方法执行
        Objects res = (Objects) method.invoke(obj, args);
        // 方法之后
        System.out.println("执行方法后." + obj);
        return res;
    }
}

public class UserDaoJDKImplProxy {

    public static void main(String[] args) {
        // 创建接口实现类代理对象, 参数：类加载器，实现的接口，增强方法
        Class[] interfaces = {UserJDKDao.class};
        UserJDKDaoImpl userJDKDao = new UserJDKDaoImpl();
        UserJDKDao dao =  (UserJDKDao) Proxy.newProxyInstance(UserDaoJDKImplProxy.class.getClassLoader(), interfaces, new UserDaoJDKInvocation(userJDKDao));
        dao.login("namea");
    }
}
