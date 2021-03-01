package com.phoenixhell.work;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * 注意 1.futureTask.get()回阻塞当前主线程
 *     2.futureTask有缓存 同一个对象run任务不会重新运行必须新建一个
 *     main方法要在所有线程之后调用可以等所有get完成
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> list =new CopyOnWriteArrayList<>();

        FutureTask<List<String>> futureTask1 =new FutureTask<>(()-> {
            TimeUnit.SECONDS.sleep(1);
            list.add(UUID.randomUUID().toString().substring(0,6));
            return list;
        });
        FutureTask<List<String>> futureTask2 =new FutureTask<>(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                list.add(UUID.randomUUID().toString().substring(0,6));
                return list;
            }
        });
        FutureTask<List<String>> futureTask3=new FutureTask<>(()-> {
            TimeUnit.SECONDS.sleep(3);
            list.add(UUID.randomUUID().toString().substring(0,6));
            return list;
        });
        new Thread(futureTask1,String.valueOf(1)).start();
        new Thread(futureTask2,String.valueOf(2)).start();
        new Thread(futureTask3,String.valueOf(3)).start();


        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        System.out.println(futureTask3.get());
        System.out.println("*******************");
        System.out.println(list);
    }

}
