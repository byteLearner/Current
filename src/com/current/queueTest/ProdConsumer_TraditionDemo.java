package com.current.queueTest;

/**
 * @description: 传统版生产消费者模式
 * @author: liuqiang
 * @time: 2021/2/25 14:31
 */

import com.current.model.ShareData;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，来5轮
 *
 * 口诀：
 *  1.线程操作资源类     --> 编写方法
 *  2.判断 干活 通知     --> await() 和 signalAll()
 *  3.防止虚假唤醒机制   --> 使用 while 判断，而不是 if
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                shareData.increment();
            },"prod"+i).start();
        }

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                shareData.decreament();
            },"consumer"+i).start();
        }
    }
}
