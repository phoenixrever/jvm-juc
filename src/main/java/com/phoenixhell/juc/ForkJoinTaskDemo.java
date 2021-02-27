package com.phoenixhell.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer> {
    private Integer TASK_VALUE = 10;
    private Integer begin;
    private Integer end;
    private Integer result = 0;

    public MyTask(Integer begin, Integer end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        //递归的最终计算条件绝对不能少
        if ((end - begin) < 10) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
            return result;
        } else {
            /*
            绝对不能这么写10-20 10-15 16-20
            这么写就变成10-10  11-20  类似 报错
            MyTask myTask01 = new MyTask(begin, end / 2);
            MyTask myTask02 = new MyTask(end / 2 + 1, end);
            */
            int middle=(begin+end)/2;
            MyTask myTask01 = new MyTask(begin, middle);
            MyTask myTask02 = new MyTask(middle + 1, end);
            myTask01.fork();
            myTask02.fork();
            result=myTask01.join()+myTask02.join();
        }
        return result;
    }
}

public class ForkJoinTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinTask<Integer> myTask = new MyTask(0, 100);
        ForkJoinPool forkJoinPool =new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        System.out.println(forkJoinTask.get());
    }
}
