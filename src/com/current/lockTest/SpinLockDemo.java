package com.current.lockTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 自旋锁代码示例
 *
 * @author: liuqiang
 * @time: 2021/2/25 10:49
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        System.out.println(Thread.currentThread().getName()+"come in");
        while (!atomicReference.compareAndSet(null,Thread.currentThread())){
        }
    }
    public void myUnlock() {
        System.out.println(Thread.currentThread().getName()+"come out");
        atomicReference.compareAndSet(Thread.currentThread(),null);
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
                spinLockDemo.myUnlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
                spinLockDemo.myUnlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
