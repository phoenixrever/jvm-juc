package com.phoenixhell.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    public static void main(String[] args) {
        /**
         *  public Semaphore(int permits, boolean fair) {
         *      sync = fair ? new FairSync(permits) : new NonfairSync(permits);
         *  }
         */
        Semaphore semaphore =new Semaphore(3,false);

        for(int i=0;i<7;i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t take the position");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"\t after 1s leave the position");
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
