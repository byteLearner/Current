package com.current.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 用于测试volatile线程可见性
 * @author: liuqiang
 * @time: 2021/2/24 14:40
 */
public class Mydata {
    //public int number = 0;
    // volatile可以保证可见性，及时通知其它线程主物理内存的值已被修改
    public volatile int number = 0;

    public void setNumber() {
        this.number = 60;
    }
    //此时number前面已经加了volatile，但是不保证原子性
    public void increment() {
        number++;
    }

    // Integer 原子包装类
    public AtomicInteger atomicInteger = new AtomicInteger(0);
    public void atomicIncrement() {
        atomicInteger.getAndIncrement();
    }
}
