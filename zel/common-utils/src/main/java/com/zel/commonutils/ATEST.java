package com.zel.commonutils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ATEST {
    private static BlockingQueue<Integer> insertQueue = new LinkedBlockingQueue<>();
    private static List<Integer> insertList;
    private static BlockingQueue<Integer> updateQueue = new LinkedBlockingQueue<>();
    private static List<Integer> updateList;

    public static void main(String[] args) {
        final int limit = 64;
        new Thread(new Runnable() {
            int count = 0;// 计时器10次等于1秒
            @SuppressWarnings("static-access")
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        System.out.println("ddd" + count);
                        if (insertQueue.isEmpty() && count <= 10) {
                            count++;
                            count = count % 12;
                            try {
                                Thread.currentThread().sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();

                            }
                        } else {
                            if (insertList == null) {
                                insertList = new LinkedList<Integer>();
                            }

                            Integer po = insertQueue.poll();
                            if (po != null) {
                                insertList.add(po);
                            }

                            // 时间触发查询动作 或 超过临界值
                            if ((count >= 10 && insertList != null && insertList.size() > 0) || insertList.size() >= limit) {
//                                Integer.getInstance().insert(insertList);
                                // 将查询状态设置为初始状态
                                insertList = null;
                            }
                            count = 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // 将查询状态设置为初始状态
                        count = 0;
                        insertList = null;
//                        SysLoggers.back_service_log.error("MonitorRecordInsertService|输出任务定时器异常|" + e.getMessage());
                    }
                }
            }
        }).start();
    }
}
