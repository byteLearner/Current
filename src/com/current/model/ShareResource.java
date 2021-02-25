package com.current.model;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 多线程交替打印，展示 Lock 的精确唤醒资源类
 * @author: liuqiang
 * @time: 2021/2/25 14:53
 */
public class ShareResource {
    private volatile AtomicInteger atomicInteger = new AtomicInteger(0);
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try{
            while (atomicInteger.get() != 0) {
                condition1.await();
            }
            for (int i = 0 ; i < 5; i++) {
                System.out.println(Thread.currentThread().getName());
            }
            atomicInteger.set(1);
            condition2.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try{
            while (atomicInteger.get() != 1) {
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName());
            }
            atomicInteger.set(2);
            condition3.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try{
            while (atomicInteger.get() != 2) {
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName());
            }
            atomicInteger.set(0);
            condition1.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
