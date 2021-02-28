package com.phoenixhell.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue =new ArrayBlockingQueue<>(3);
        //add插入元素
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        //Exception in thread "main" java.lang.IllegalStateException: Queue full
//        blockingQueue.add("x");

/*        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/


       //remove 移除元素 不按照顺序 true
        //没有元素的时候抛异常 Exception in thread "main" java.util.NoSuchElementException
        System.out.println(blockingQueue.remove("b"));
        //remove 移除元素 按照先进先出顺序 a
        System.out.println(blockingQueue.remove());

        //查看要出队列的元素 没有抛异常 c
        System.out.println(blockingQueue.element());

//        //false add 满了抛异常  offer满了返回true
//        boolean x = blockingQueue.offer("X");
////        System.out.println(x);
//
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        //poll没有元素的时候返回null
//        System.out.println(blockingQueue.poll());
//
//        //查看要出队列的元素 没有返回null
//        blockingQueue.peek();
//        //put void 加不进去一直阻塞
//        blockingQueue.put("a");
//        blockingQueue.put("a");
//        blockingQueue.put("a");
//        blockingQueue.put("a");
//        //没有元素一直阻塞 等待取出
//        blockingQueue.take();
//
//        //插入元素a 没有位置就阻塞 3秒停止阻塞
//        blockingQueue.offer("a", 3L, TimeUnit.SECONDS);
//        //取出元素a 没有位置就阻塞 3秒停止阻塞
//        blockingQueue.poll(3L, TimeUnit.SECONDS);
//        System.out.println(blockingQueue);
    }
}
