package com.zel.market.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 批量操作任务队列
 *	    抛出异常	特殊值	    阻塞     超时
 * 插入	add(e)	offer(e)	put(e)	offer(e, time, unit)
 * 移除	remove()	poll()	take()	poll(time, unit)
 * 检查	element()	peek()	不可用	不可用
 *
 */
public class BatchTaskQueue {

    private static final LinkedBlockingQueue<Object> insertQueue = new LinkedBlockingQueue<>();

    private static final LinkedBlockingQueue<Object> updateQueue = new LinkedBlockingQueue<>();

    private BatchTaskQueue() {

    }

    private static class BatchTaskQueueHandler {
        private static BatchTaskQueue instance = new BatchTaskQueue();
    }

    public static BatchTaskQueue getInstance() {
        return BatchTaskQueueHandler.instance;
    }

    /**
     * 插入一条数据
     * @param esdata
     */
    public void insert(Object esdata) {
        if(null != esdata) {
            try {
                insertQueue.put(esdata);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取插入队列长度
     * @return
     */
    public int getInsertSize() {
        return insertQueue.size();
    }

    /**
     * 获取插入数据列表
     * @param size
     * @return
     */
    public synchronized List<Object> getInsertList(int size) {
        if(size <= 0) {
            return null;
        }
        List<Object> list = new ArrayList<Object>(size);
        for(int i = 0; i < size; ++i) {
            try {
                list.add(insertQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 更新一条数据
     * @param udata
     */
    public void update(Object udata) {
        if(null != udata) {
            try {
                updateQueue.put(udata);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取更新队列长度
     * @return
     */
    public int getUpdateSize() {
        return updateQueue.size();
    }

    public synchronized List<Object> getUpdateList(int size) {
        if(size <= 0) {
            return null;
        }
        List<Object> list = new ArrayList<Object>(size);
        for(int i = 0; i < size; ++i) {
            try {
                list.add(updateQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
