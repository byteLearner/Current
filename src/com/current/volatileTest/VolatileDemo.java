package com.current.volatileTest;

import com.current.model.Mydata;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程可见性测试
 * @author: liuqiang
 * @time: 2021/2/24 14:35
 */
public class VolatileDemo {
    public static void main(String[] args) {
        volatileVisibilityDemo();
    }

    /*
    验证volatile的可见性
        1.1 加入int number=0，number变量之前根本没有添加volatile关键字修饰，没有可见性
        1.2 添加了volatile，可以解决可见性问题
     */
    public static void volatileVisibilityDemo() {
        System.out.println("线程可见性测试");
        Mydata myData = new Mydata();//资源类

        //启动一个线程操作共享数据
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"线程\t开始操作");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.setNumber();
                System.out.println(Thread.currentThread().getName()+"number:"+myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        while (myData.number == 0) {
            //main线程持有共享数据的拷贝，一直为0
        }
        System.out.println(Thread.currentThread().getName()+"\tmission over");
    }
}
