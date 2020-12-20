package com.zel.market.jobs;

/**
 * Description: 定时任务
 * 1. 直接用 MySQL 存储，超时时间戳加索引
 * 2. 周期性从 MySQL 中将超时的任务批量取出，缓存在内存中
 * 3. worker 直接从内存中拿定时器任务，进行处理；处理完毕后更新 mysql 中的状态
 */
public class JOBS {
}
