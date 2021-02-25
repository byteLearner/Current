package com.current.queueTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: SynchronousQueue阻塞队列演示
 * @author: liuqiang
 * @time: 2021/2/25 14:19
 */
public class SynchronousQueueDemo {
    /**
     * SynchronousQueue没有容量。与其他BlockingQueue不同，SynchronousQueue是一个不存储元素的BlockingQueue。
     * 每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。
     * 一句话总结：SynchronousQueue 时零库存阻塞队列
     * */
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new SynchronousQueue<>();

            new Thread(() -> {
                for (int i = 0; i < 3; i++) {
                    try {
                        System.out.println(Thread.currentThread().getName()+"向队列添加元素"+i);
                        blockingQueue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"AA").start();

            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"线程获取到元素："+blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"线程获取到元素："+blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"线程获取到元素："+blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"BB").start();
    }
}
