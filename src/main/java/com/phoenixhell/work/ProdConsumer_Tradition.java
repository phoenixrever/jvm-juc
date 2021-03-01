package com.phoenixhell.work;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author phoenixhell
 * @since 2021/3/1 0001-上午 10:18
 * 题目 初始值为0的变量 2个线程交通操作 一个加1 一个减1 来5轮
 * 高内聚 -> 所有操作我自身的方法 我都带着
 * 低耦合 ->
 *
 * 1 线程   操作(方法)  资源类
 * 2 判断   干活  通知
 * 3 防止虚假判断 多线程用while
 */
class SharedData{
    private Integer number = 0;
    private Lock lock =new ReentrantLock();
    private Condition condition =lock.newCondition();
    public void increment() {
        lock.lock();
        try {
            //1判断 防止虚假判断 多线程用while
            while(number!=0){
                //使当前线程等待，直到发出信号或被中断为止
                //新版本 lock await() signal() 老版本 sync wait() notify()
                condition.await();
            }
            //2干活 生产
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);

            //3通知唤醒  this.notify()
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            //1判断 防止虚假判断 多线程用while
            while(number!=1){
                //使当前线程等待，直到发出信号或被中断为止
                //新版本 lock await() signal() 老版本 sync wait() notify()
                condition.await();
            }
            //2干活 消费
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);

            //3通知唤醒  this.notify()
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ProdConsumer_Tradition {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                sharedData.increment();
            }
        },"AAA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                sharedData.decrement();
            }
        },"BBB").start();
    }
}
