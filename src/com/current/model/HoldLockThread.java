package com.current.model;

import java.util.concurrent.TimeUnit;

/**
 * @description: 死锁演示
 * @author: liuqiang
 * @time: 2021/2/26 16:33
 */
public class HoldLockThread implements Runnable {
    private String lock1;
    private String lock2;

    public HoldLockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        /**
         *  new Thread(new HoldLockThread(lock1,lock2),"AA").start();
         *  new Thread(new HoldLockThread(lock2,lock1),"BB").start();
         * */
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName()+"自己获取"+lock1+"尝试获取"+lock2);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName()+"自己获取"+lock2+"尝试获取"+lock1);
            }
        }

    }
}
