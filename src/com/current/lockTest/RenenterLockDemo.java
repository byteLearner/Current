package com.current.lockTest;

import com.current.model.Phone;

/**
 * @description: 可重入锁演示
 * @author: liuqiang
 * @time: 2021/2/24 21:16
 */
public class RenenterLockDemo {
    /*
     * 可重入锁（也就是递归锁）
     *
     * 指的是同一个线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
     * 在同一线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
     *
     * 也就是说，线程可以进入任何一个它已经拥有的锁所有同步着的代码块。
     *
     * t1   invoked sendSMS()      t1线程在外层方法获取锁的时候
     * t1   invoked sendEmail()    t1在进入内层方法会自动获取锁
     * t2   invoked sendSMS()
     * t2   invoked sendEmail()
     *
     */
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(phone::sendSMS,"A").start();
        new Thread(() -> {
            phone.sendSMS();
        },"B").start();
    }
}
