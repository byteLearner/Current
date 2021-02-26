package com.current.lockTest;

import com.current.model.HoldLockThread;

/**
 * @description: 死锁演示
 * @author: liuqiang
 * @time: 2021/2/26 16:32
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lock1 = "lock1";
        String lock2 = "lock2";
        new Thread(new HoldLockThread(lock1,lock2),"AA").start();
        new Thread(new HoldLockThread(lock2,lock1),"BB").start();
    }
}
