package com.phoenixhell.work;

import java.util.concurrent.*;

/**
 * @author phoenixhell
 * @since 2021/3/2 0002-上午 9:04
 * 核心加阻塞队列满了且正在运行的线程数量还小于maximumPoolSize，
 * 那么还是要创建非核心线程立刻运行这个任务；(立即加塞 不经过阻塞队列
 * 678直接占用后来创建的线程 让后才是阻塞队列等待的345
 */

public class ThreadPoolExecutor7params {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(2,
                5,
                100L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        //模拟6个顾客来办理业务 5 个窗口
        try {
            for (int i = 1; i <= 8; i++) {
                int finalI = i;
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 顾客"+ finalI +"来办理业务");
                    try {TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();//关闭线程池不能忘记
        }
    }
}
