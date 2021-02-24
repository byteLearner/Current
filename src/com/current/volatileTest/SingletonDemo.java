package com.current.volatileTest;

/**
 * @description: DCL模式单例模式实现
 * @author: liuqiang
 * @time: 2021/2/24 16:04
 */
public class SingletonDemo {
    //private static SingletonDemo singletonDemo = null;
    //加上 volatile ，禁止指令重排
    private static volatile SingletonDemo singletonDemo = null;

    private SingletonDemo() {
        System.out.println("构造方法被调用");
    }

    /*
    * 这种写法在多线程条件下可能正确率为 99.999999%，但可能由于指令重排出错
    * DCL模式 Double Check Lock 双端检索机制：在加锁前后都进行判断
    * */
    public static SingletonDemo getSingletonDemo() {
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                SingletonDemo.getSingletonDemo();
            },String.valueOf(i)).start();
        }
    }
}
