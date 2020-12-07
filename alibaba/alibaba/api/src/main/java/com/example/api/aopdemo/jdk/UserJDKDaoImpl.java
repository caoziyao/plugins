package com.example.api.aopdemo.jdk;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */
public class UserJDKDaoImpl implements UserJDKDao {
    @Override
    public void login(String name) {
        System.out.println("登录成功");
    }
}
