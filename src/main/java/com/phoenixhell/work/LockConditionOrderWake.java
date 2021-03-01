package com.phoenixhell.work;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author phoenixhell
 * @since 2021/3/1 0001-上午 11:00
 * A 打印3次 B 5次 C 10次
 * condition signal
 */
class PrintOrder{
    private Integer printCount = 3;
    private Lock lock =new ReentrantLock();
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();

    public void print3(){
        lock.lock();
        try {
            while(printCount!=3){
                condition1.await();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+"\t 打印了"+i+"次");
            }
            printCount=5;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print5(){
        lock.lock();
        try {
            while(printCount!=5){
                condition2.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t 打印了"+i+"次");
            }
            printCount=10;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try {
            while(printCount!=10){
                condition3.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t 打印了"+i+"次");
            }
            printCount=3;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class LockConditionOrderWake {
    public static void main(String[] args) {
        PrintOrder printOrder = new PrintOrder();
        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                printOrder.print3();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                printOrder.print5();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                printOrder.print10();
            }
        },"C").start();
    }
}
