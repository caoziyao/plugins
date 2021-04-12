package com.zel.market;

import com.zel.dbmanager.mapper.UserMapper;
import com.zel.market.app.service.user.UserService;
import com.zel.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 创建一个mybatis-plus数据库
 * DROP TABLE IF EXISTS user;
 *
 * CREATE TABLE user
 * (
 * 	id BIGINT(20) NOT NULL COMMENT '主键ID',
 * 	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
 * 	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
 * 	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
 * 	PRIMARY KEY (id)
 * );
 *
 *
 */
public class DBTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = new User();
        user.setAge(123);
        userMapper.insert(user);
    }
}
