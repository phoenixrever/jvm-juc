package com.phoenixhell.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Order implements Delayed {
    private Long delayTime=null;
    private int index=0;


    //返回与此对象相关的剩余延迟时间，以给定的时间单位表示。
    //现在的时间 减去设置的过期时间>0 说明过期了
    @Override//
    public long getDelay(TimeUnit unit) {
        //  设置时间减去当前时间 大于0说明没到时间
        return unit.convert(delayTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    //>0 升序 从小到大 小的delay剩余时间在队头
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.SECONDS)-o.getDelay(TimeUnit.SECONDS));
    }
}
public class DelayQueueDemo {
    public static void main(String[] args) {
        DelayQueue<Order> orders = new DelayQueue<>();
        new Thread(()->{
            for (int i = 0; i <3 ; i++) {
                orders.offer(new Order(System.currentTimeMillis()+1000*3,i));
                try{
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            orders.offer(new Order(System.currentTimeMillis()+1000*20,100));

        },"put").start();
        new Thread(()->{
            try {
                while (true) {
                    Order take = orders.take();
                    System.out.println("stacking-----------");
                    System.out.println(take);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"take").start();

    }
}
