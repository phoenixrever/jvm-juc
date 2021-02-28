package com.phoenixhell.work;

import com.phoenixhell.enumdemo.SixKingdom;

import java.util.concurrent.CountDownLatch;
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch =new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国被灭");
                countDownLatch.countDown();
            }, SixKingdom.get(i)).start();
        }
        countDownLatch.await();
        System.out.println("main is complete");
    }

    private static void countDown() throws InterruptedException {
        CountDownLatch countDownLatch =new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"运行完成");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("main is complete");
    }
}
