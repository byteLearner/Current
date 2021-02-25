package com.current.queueTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue:是一个基于数组结构的有界阻塞队列，此队列按FIFO原则对元素进行排序
 * LinkedBlockingQueue:是一个基于链表结构的阻塞队列，此队列按FIFO排序元素，吞吐量高于ArrayBlockingQueue
 * SynchronousQueue：一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移出操作，否则插入操作一直处于阻塞状态，吞吐量通常要高
 *
 * 阻塞队列
 *   1.阻塞队列有没有好的一面
 *   2.不得不阻塞，你如何管理
 *
 * @ClassName BlockingQueueDemo
 * @Description TODO
 * @Author Heygo
 * @Date 2020/8/8 19:11
 * @Version 1.0
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        //addAndRemove(blockingQueue);
        //offerAndPoll(blockingQueue);
        //putAndTake(blockingQueue);
        outOfTime(blockingQueue);
    }

    /**
     * 当阻塞队列满时，再往队列里add插入元素会抛IllegalStateException:Queue full
     * 当阻塞队列空时，再往队列里remove移除元素会抛NoSuchElementException
     * */
    private static void addAndRemove(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.add("e"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }

    /**
     * 插入方法，成功 ture 失败 false
     * 移除方法，成功返回出队列的元素，队列里面没有就返回null
     * */
    private static void offerAndPoll(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("e"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * 当阻塞队列满时，生产者线程继续往队列里put元素，队列会一直阻塞生产线程直到put数据成功或者响应中断退出。
     * 当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用。
     * */
    private static void putAndTake(BlockingQueue<String> blockingQueue) throws InterruptedException {
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    /**
     * 当阻塞队列满时，队列会阻塞生产者线程一定时间，超过后限时后生产者线程会退出
     * */
    private static void outOfTime(BlockingQueue<String> blockingQueue) throws InterruptedException {
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
    }

}

