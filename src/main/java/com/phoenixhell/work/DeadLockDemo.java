package com.phoenixhell.work;

import java.util.concurrent.TimeUnit;

/**
 * @author phoenixhell
 * @since 2021/3/2 0002-上午 10:26
 */

class DeadResource implements Runnable{
    private String lockA;
    private String lockB;

    public DeadResource(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己的锁lockA");
            try {
                TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己的锁lockB");
                try {TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        DeadResource deadResourceA = new DeadResource("lockA","lockB");
        DeadResource deadResourceB = new DeadResource("lockB","lockA");
        new Thread(deadResourceA,"lockA").start();
        new Thread(deadResourceB,"lockB").start();
//        long totalMemory = Runtime.getRuntime().totalMemory();
//        long maxMemory = Runtime.getRuntime().maxMemory();
//        System.out.println("TOTAL_MEMORY(-Xms默认内存的1/64)：" + totalMemory/1024/1024 + "M");//初始化堆内存
//        System.out.println("MAX_MEMORY(-Xmx)默认内存的1/4" + maxMemory/1024/1024 + "M");

    }
}
