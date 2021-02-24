package com.current.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 可重入锁使用model,ReentrantLock演示
 * @author: liuqiang
 * @time: 2021/2/24 21:16
 */
//synchronized 示例，可重入锁使用model
/*public class Phone {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName()+"\t"+"send SMS");
        sendEmail();
    }
    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName()+"\t"+"send Email");
    }
}*/
//ReentrantLock示例
public class Phone implements Runnable{
    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        sendSMS();
    }
    public void sendSMS() {
        reentrantLock.lock();
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t"+"send SMS");
            sendEmail();
        }finally {
            //锁两次，释放一次或造成第二把锁没有释放，因此锁几次释放几次
            reentrantLock.unlock();
            reentrantLock.unlock();
        }
    }
    public void sendEmail() {
        reentrantLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t"+"send Email");
        }finally {
            reentrantLock.unlock();
        }
    }

}
