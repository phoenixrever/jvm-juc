package com.phoenixhell.work;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author phoenixhell
 * @since 2021/3/1 0001-下午 1:58
 *lock()忽视interrupt(), 拿不到锁就 一直阻塞 拿到锁运行后才会响应interrupt请求
 *lockInterruptibly()立即响应interrupt 并catch到InterruptedException
 *tryCatch 返回true 才运行
 */

public class TryLockLockInterruptibility {
    Lock lock = new ReentrantLock();

    void doWork(){
        String name = Thread.currentThread().getName();

        try {
            System.out.println(name + " 开始尝试获取锁");
            lock.lockInterruptibly();
            System.out.println(name + " 得到锁");
            System.out.println(name + " 开工干活");
            //线程在sleep或wait,join， 此时如果别的进程调用此进程的 interrupt（）方法，
            // 此线程会被唤醒并被要求处理InterruptedException；

            //此线程在运行中， 则不会收到提醒。但是 此线程的 “打扰标志”会被设置，
            // 可以通过isInterrupted()查看并 作出处理
            for (int i=0; i<5; i++) {
                //不开启阻塞是收不到interrupt信号的
                Thread.sleep(1000);
                System.out.println(name + " : " + i);
            }
        } catch (Exception e) {
            System.out.println(name + " 被中断");
            System.out.println(name + " 做些别的事情");
        } finally {
            try {
                System.out.println(name + " 释放锁");
                lock.unlock();
            } catch (Exception e) {
                System.out.println(name + " : 没有得到锁的线程运行结束");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TryLockLockInterruptibility t =new TryLockLockInterruptibility();

        Thread thread1 = new Thread(() -> {
            t.doWork();
        }, "AAA");
        thread1.start();
        Thread thread2 = new Thread(() -> {
            t.doWork();
        }, "BBB");
        thread2.start();
        //lock()忽视interrupt(), 拿不到锁就 一直阻塞 拿到锁运行后才会响应interrupt请求
        // lockInterruptibly()立即响应interrupt 并catch到InterruptedException
        //tryCatch 返回true 才运行
        thread2.interrupt();
    }
}


