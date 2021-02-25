package com.current.model;

import com.sun.corba.se.impl.orbutil.ObjectUtility;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 读写锁演示
 * @author: liuqiang
 * @time: 2021/2/25 11:15
 */
public class MyCache {
    // 凡缓存，一定要用 volatile 修饰，保证内存可见性
    private volatile Map<String,String> map = new HashMap<>();
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void write(String key,String value) {
        reentrantReadWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

    public String read(String key) {
        reentrantReadWriteLock.readLock().lock();
        String value = null;
        try{
            System.out.println(Thread.currentThread().getName() + "\t 正在读取：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完成"+value);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
        return value;
    }
}
