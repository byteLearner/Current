package com.current.lockTest;

import com.current.model.MyCache;

/**
 * @description: 读写锁演示
 * @author: liuqiang
 * @time: 2021/2/25 11:13
 */
public class ReadWriteLockDemo {
    /**
     * 多个线程同时读一个资源类没有问题，所以为了满足并发量，读取共享资源应该可以同时进行。
     *
     * 但是写资源只能有一个线程。
     *
     * 写操作：原子+独占，整个过程必须是一个完整的统一体，中间不许被分割，被打断。
     *
     * 小总结：
     * 读-读能共存
     * 读-写不能共存
     * 写-写不能共存
     *
     * 写操作：原子性+独占，整个过程必须是一个完整的统一体，中间不许被分隔，被打断
     * */
    public static void main(String[] args){
        MyCache myCache = new MyCache();
        for (int i = 0; i < 10; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.write(temp+"",temp+"");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.read(temp+"");
            },String.valueOf(i)).start();
        }
    }
}
