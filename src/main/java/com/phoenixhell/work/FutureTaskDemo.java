package com.phoenixhell.work;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 注意 1.futureTask.get()回阻塞当前主线程
 *      2.futureTask有缓存 同一个对象run任务不会重新运行必须新建一个
 *      main方法要在所有线程之后调用可以等所有get完成
 *
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            System.out.println("come in callable");
            try{
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1024;
        });

        new Thread(futureTask,"AA").start();
        //come in callable 只会打印一次
        //futureTask有缓存 同一个对象run任务不会重新运行必须新建一个
        new Thread(futureTask,"BB").start();
        int result=100;
//        while(!futureTask.isDone()){
//
//        }
        System.out.println(result+futureTask.get());
    }
}
