package com.current.communication;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: CyclicBarrier线程通讯演示
 * @author: liuqiang
 * @time: 2021/2/25 10:05
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * CyclicBarrier 字面意思是可循环使用的屏障。它要做的事情是，让一组线程到达一个屏障时被阻塞，
         * 直到最后一个线程到达屏障时，屏障才会打开，所有被屏障拦截的线程才会继续干活
         * */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new Runnable() {
            @Override
            public void run() {
                System.out.println("召唤神龙");
            }
        });
        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"线程启动");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
