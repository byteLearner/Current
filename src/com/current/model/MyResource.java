package com.current.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: liuqiang
 * @time: 2021/2/25 15:31
 */
public class MyResource {
    private volatile boolean flag = true;
    private BlockingQueue<String> blockingQueue = null;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void produce() throws InterruptedException {
        String val = null;
        boolean valFlag = false;
        while (flag) {
            val = atomicInteger.incrementAndGet()+"";
            valFlag = blockingQueue.offer(val, 2L, TimeUnit.SECONDS);
            if (valFlag == true) {
                System.out.println(Thread.currentThread().getName()+"插入"+val+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"插入"+val+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"停止生产");
    }

    public void consumer() throws InterruptedException {
        String val = null;
        while (flag) {
            val = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (val == null|| val.equals("")) {
                flag=false;
                System.out.println(Thread.currentThread().getName()+"超时退出");
            }else {
                System.out.println(Thread.currentThread().getName()+"取出"+val+"成功");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"停止消费");
    }

    public void stop() {
        flag = false;
    }
}
