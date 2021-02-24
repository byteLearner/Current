package com.current.CASTest;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description: CAS算法ABA问题
 * @author: liuqiang
 * @time: 2021/2/24 16:43
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        System.out.println("<---------ABA问题------------->");
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"线程启动");
            try { TimeUnit.SECONDS.sleep(1);
                atomicReference.compareAndSet(100,111);
                atomicReference.compareAndSet(111,100);
            } catch (InterruptedException e) { e.printStackTrace(); }

        },"AA").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"线程启动");
            try {
                TimeUnit.SECONDS.sleep(3);
                atomicReference.compareAndSet(100,120);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"BB").start();
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"值为"+atomicReference.get());

        System.out.println("<---------解决ABA问题------------->");
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"线程启动");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"\t第一次版本号"+atomicStampedReference.getStamp());
                atomicStampedReference.compareAndSet(100,111,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"\t第二次版本号"+atomicStampedReference.getStamp());
                atomicStampedReference.compareAndSet(111,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"\t第三次版本号"+atomicStampedReference.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"CC").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"线程启动");
            System.out.println(Thread.currentThread().getName()+"获取到版本号"+atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(3);
                boolean flag = atomicStampedReference.compareAndSet(100, 120, stamp, atomicStampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName()+"修改结果为"+flag);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"DD").start();
        while (Thread.activeCount() > 2){}
        System.out.println(Thread.currentThread().getName()+"值为"+atomicStampedReference.getReference());
    }
}
