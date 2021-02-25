package com.current.communication;

import com.current.model.CountryEnum;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @description: CountDownLatch线程通讯演示
 * @author: liuqiang
 * @time: 2021/2/25 9:46
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //让一些线程阻塞，直到另一些线程完成一系列操作后才被唤醒
        CountDownLatch countDownLatch = new CountDownLatch(6);
        System.out.println(Thread.currentThread().getName()+"启动");
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"启动");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, CountryEnum.getEnumCountry(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"结束");
    }
}
