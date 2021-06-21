package com.zel.market.demo.mapper;

import com.zel.market.demo.model.User;
import com.zel.market.demo.model.UserExample;
import java.util.List;
//import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(User record,  UserExample example);

    int updateByExample( User record,  UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}