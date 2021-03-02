package com.phoenixhell.juc;

import java.util.concurrent.*;

public class CustomThreadPool {
    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(Runtime.getRuntime().availableProcessors()+"个逻辑处理器");
        /*
            cpu密集型任务
           最大线程数通常是逻辑处理器个数加1或者2  即12+1
            IO 密集型任务
            cpu核数/阻塞系数
            阻塞系数：线程花在系统IO上的时间与cpu密集任务所耗的时间比值
         */
        //静态内部类与非静态内部类创建区别
        CustomThreadPool customThreadPool = new CustomThreadPool();
        CustomThreadPool.InnerClassTest innerClassTest = customThreadPool.new InnerClassTest();
        new CustomThreadPool.InnerClassTest2();


        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,// 最大线程数通常是逻辑处理器个数加1或者2  即12+1
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                //AbortPolicy(默认)：直接抛出RejectedExecutionException异常阻止系统正常运行
//                new ThreadPoolExecutor.AbortPolicy());
                //CallerRunsPolicy：“调用者运行”一种调节机制，该策略既不会抛弃任务，也不会抛出异常，
                // 而是将某些任务回退到调用者(main)，从而降低新任务的流量。
                //一句话 回退 哪个线程调用这个任务的找谁（main）执行任务
//                new ThreadPoolExecutor.CallerRunsPolicy());
                //DiscardPolicy：该策略默默地丢弃无法处理的任务
                // 不予任何处理也不抛出异常。 如果允许任务丢失，这是最好的一种策略。
                new ThreadPoolExecutor.DiscardPolicy());
                //DiscardOldestPolicy：抛弃队列中等待最久的任务，
                // 然后把当前任务加人队列中 尝试再次提交当前任务。
//                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            for (int i = 1; i <=9; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"-----执行任务");
                    try {TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            threadPoolExecutor.shutdown();
        }
    }
   class InnerClassTest{
        public InnerClassTest() {
        }
    }

    static class InnerClassTest2{
        public InnerClassTest2() {
        }
    }
}
