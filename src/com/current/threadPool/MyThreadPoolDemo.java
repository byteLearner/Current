package com.current.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: newFixedThreadPool、newSingleThreadExecutor、newCachedThreadPool线程池演示
 * @author: liuqiang
 * @time: 2021/2/26 15:40
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        myThread();
    }

    public static void myThread() {
        //固定线程数量的线程池
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        //一池1个线程
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //不定量线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        try{
            for (int i = 0; i < 10; i++) {
                executorService.execute(() ->{
                    System.out.println(Thread.currentThread().getName()+"正在处理");
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
