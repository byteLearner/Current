package com.current.threadPool;

import java.util.concurrent.*;

/**
 * @description: 线程池拒绝策略演示
 * @author: liuqiang
 * @time: 2021/2/26 15:49
 */
public class RejectPolicy {
    public static void main(String[] args) {
        myThreadPool();
    }
    public static void myThreadPool() {
        ExecutorService executorService = new ThreadPoolExecutor(
              2,
              5,
              1L,
              TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                //指令阻塞队列上限 + 使用默认的 AbortPolicy 策略
                //new ThreadPoolExecutor.AbortPolicy()
                //使用 CallerRunsPolicy 策略
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //使用 DiscardOldestPolicy 策略
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                //使用 DiscardPolicy 策略
                new ThreadPoolExecutor.DiscardPolicy()
        );
        try{
            for (int i = 0; i < 9; i++) {
                int temp = i;
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"线程正在处理"+temp);
                });
            }
        }catch (Exception E) {
            E.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
