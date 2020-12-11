package com.zel.dbmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zel.dbmanager.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("SELECT * FROM user")
    List<User> findAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") String id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM user WHERE username = #{username} and password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

//    @Insert("INSERT INTO user(username, age) VALUES(#{username}, #{age})")
//    int insert(@Param("username") String username, @Param("age") Integer age);

//    @Insert("INSERT INTO user(username, password) VALUES(#{username}, #{password})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    int insertUser(@Param("username") String username, @Param("password") String password);

//    @Update("UPDATE user SET age=#{age} WHERE username=#{username}")
//    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

    /**
     * 更新GoodInfo
     */
    int updateUser(String id, String username);
}