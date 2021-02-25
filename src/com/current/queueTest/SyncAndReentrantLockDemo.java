package com.current.queueTest;

/**
 * @description: 多线程交替打印，展示 Lock 的精确唤醒示例
 * @author: liuqiang
 * @time: 2021/2/25 14:53
 */

import com.current.model.ShareResource;

/**
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 * A打印5次，B打印10次，C打印15次
 * 紧接着
 * A打印5次，B打印10次，C打印15次
 * ......
 * 打印10轮
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                shareResource.print5();
            },"A").start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                shareResource.print10();
            },"B").start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                shareResource.print15();
            },"C").start();
        }
    }
}
