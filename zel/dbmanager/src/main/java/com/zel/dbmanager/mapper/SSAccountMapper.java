package com.zel.dbmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zel.dbmanager.entity.SSAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SSAccountMapper extends BaseMapper<SSAccount> {
    void insertDemo(@Param("ip") String ip, @Param("location") String location);
    void insertAccount(SSAccount ssAccount);
    //void insert(String location, String ip, String port, String password, String encry, String protocol, String plain);
}
