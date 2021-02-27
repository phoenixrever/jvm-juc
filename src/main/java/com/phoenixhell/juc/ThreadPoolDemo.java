package com.phoenixhell.juc;

import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final int tempI=i;
                threadPool.execute(()->{

                    System.out.println(Thread.currentThread().getName()+"\t"+"-"+tempI+"-is execute");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }
    }
}
