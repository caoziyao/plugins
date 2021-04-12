package com.zel.dbmanager;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 配置类
 */
@Component
@Slf4j
public class MyMetaobjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log. info("start insert fill.....");
        System.out.println("start insert fill.....");
        //setFieldValByName(String fieldName, object fieldVal, Metaobject metaobject
        this.setFieldValByName ("createTime", new Date(), metaObject);
        this.setFieldValByName ("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log. info("start  update fill.....");
        this.setFieldValByName ("updateTime", new Date(), metaObject);
    }
}