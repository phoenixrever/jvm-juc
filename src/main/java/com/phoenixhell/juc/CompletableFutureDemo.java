package com.phoenixhell.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("this a no return value method");
        });
        System.out.println(voidCompletableFuture.get());
        System.out.println("*******************************");
        Integer integer = CompletableFuture.supplyAsync(() -> {
//            int age=10/0;
            return 3 * 3;
        }).whenComplete((r, e) -> {
            System.out.println(r);
            System.out.println(e);
        }).exceptionally(e -> {
            System.out.println(e);
            return 404;
        }).get();
        System.out.println(integer);
    }
}
