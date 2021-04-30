package com.zel.commonutils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

/**
 *  IdUtil.createSnowflake每次调用会创建一个新的Snowflake对象，不同的Snowflake对象创建的ID可能会有重复，因此请自行维护此对象为单例，
 *  或者使用IdUtil.getSnowflake使用全局单例对象。
 */
public class SnowflakeConfig {

    private long workerId = 0; //为终端ID
    private long datacenterId = 1; //数据中心ID
    private Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);

    public SnowflakeConfig(){
        workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        System.out.println("当前机器的workId:" + workerId);
    }

    public synchronized long snowflakeId(){
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long datacenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {

        SnowflakeConfig snow = new SnowflakeConfig();



        for (int i = 0; i < 10; i += 1) {
            long id = snow.snowflakeId();
            System.out.println(id);
        }
    }
}
