package com.phoenixhell.work;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author phoenixhell
 * @since 2021/2/28 0028-下午 2:15
 */

class Phone implements Runnable{
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
        try {TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        try {TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println(Thread.currentThread().getName()+"\t ###########invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        lock.lock();//无论几把都行 但是必须要成对
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked set()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }
}

public class ReentrantRecursiveLock {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        for(int i = 0; i < 3; i++) {
            new Thread(()->{
                try {
                    phone.sendEmail();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

        }
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        Thread thread1 = new Thread(phone);
        thread1.start();
        Thread thread2 = new Thread(phone);
        thread2.start();
    }
}
