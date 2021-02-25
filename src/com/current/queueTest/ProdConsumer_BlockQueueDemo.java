package com.current.queueTest;

import com.current.model.MyResource;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: liuqiang
 * @time: 2021/2/25 15:30
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"开始启动");
                myResource.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"producer").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"开始启动");
                myResource.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myResource.stop();
    }
}
