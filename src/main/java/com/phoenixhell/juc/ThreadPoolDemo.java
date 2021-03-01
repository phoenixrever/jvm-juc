package com.phoenixhell.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        //1、corePoolSize：线程池中的常驻核心线程数
        //2、maximumPoolSize：线程池中能够容纳同时 执行的最大线程数，此值必须大于等于1
        //newFixedThreadPool创建的线程池corePoolSize和maximumPoolSize值是相等的，
        //它使用的是LinkedBlockingQueue
        // 执行长期任务性能好，创建一个线程池， 一池有N个固定的线程，有固定线程数的线程
//      ExecutorService threadPool = Executors.newFixedThreadPool(5);

        //newSingleThreadExecutor 创建的线程池corePoolSize和maximumPoolSize值都是1，
        // 它使用的是LinkedBlockingQueue
        //一个任务一个任务的执行，一池一线程
//      ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // newCachedThreadPool创建的线程池将corePoolSize设置为0，将maximumPoolSize设置为Integer.MAX_VALUE，
        // 它使用的是SynchronousQueue，也就是说来了任务就创建线程运行，当线程空闲超过60秒，就销毁线程。
        // 执行很多短期异步任务，线程池根据需要创建新线程， 但在先前构建的线程可用时将重用它们。可扩容，遇强则强
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            //模拟10个用户 每个用户一个请求线程
            for (int i = 0; i < 10; i++) {
                final int tempI = i;
                //public abstract void execute(@NotNull Runnable command)
                //public abstract <T> Future<T> submit(@NotNull Callable<T> task)
                //public abstract Future<?> submit(@NotNull Runnable task)
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 处理请求");
                });
                //先前构建的线程可用时将重用  即线程1 1个人完成了所有任务
                try {
                    TimeUnit.MILLISECONDS.sleep( 500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}