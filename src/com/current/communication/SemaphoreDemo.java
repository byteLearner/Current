package com.current.communication;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description: Semaphore线程通讯演示
 * @author: liuqiang
 * @time: 2021/2/25 10:14
 */
public class SemaphoreDemo {
    /**
     * 两个构造方法，第一个是：Semaphore(int permits)，第二个是Semaphore(int permits, boolean fair)，boolean为true为公平锁，false为非公平锁，默认为非公平锁
     *
     * 1、Semaphore 即信号量，信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
     * 2、构造器 Semaphore(int) 用于指定共享资源的数目，如果设定为 1 ，则 Semaphore 信号量退化为 Lock 锁或者 synchronized 锁
     * 3、调用 semaphore.acquire() 方法获取对共享资源的使用，调用 semaphore.release() 释放对共享资源的占用
     * 4、一句话讲明白：抢车位
     * */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3,false);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"线程获取锁");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"线程释放锁");
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }

}
