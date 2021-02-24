package com.current.volatileTest;

import com.current.model.Mydata;

import java.util.concurrent.TimeUnit;

/**
 * @description: 验证volatile关键字不能保证原子性
 * @author: liuqiang
 * @time: 2021/2/24 15:03
 */
public class VolatileDemo1 {
    public static void main(String[] args) {
        atomicDemo();
    }
    /*
    2 验证volatile不保证原子性
        2.1 原子性是不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者分割。
            需要整体完成，要么同时成功，要么同时失败。

        2.2 volatile不可以保证原子性演示

        2.3 如何解决原子性
            1）加sync
            2）使用我们的JUC下AtomicInteger
     */
    public static void atomicDemo() {
        System.out.println("原子性测试");
        Mydata mydata = new Mydata();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t正在操作");
                for (int j = 0; j < 1000; j++) {
                    mydata.increment();
                    //原子类自增
                    mydata.atomicIncrement();
                }
            },"线程"+i).start();
        }
         /*
        需要等待上述20个线程都计算完成后，再用main线程去的最终的结果是多少？
        只要上述20个线程还有在执行的，main线程便礼让，让他们执行，直至最后只剩main线程
         */
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"mission over"+mydata.number);
        System.out.println(Thread.currentThread().getName()+"mission over,原子类操作"+mydata.atomicInteger);
    }
}
