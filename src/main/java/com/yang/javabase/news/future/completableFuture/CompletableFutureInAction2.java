package com.yang.javabase.news.future.completableFuture;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/13 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class CompletableFutureInAction2 {

    public static void main(String[] args)
            throws InterruptedException {
        AtomicBoolean finished = new AtomicBoolean(false);
        ExecutorService executor = Executors.newFixedThreadPool(3, r -> {
            Thread t = new Thread(r);
            // 使线程池的现场不是守护线程,默认是守护线程
            t.setDaemon(false);
            return t;
        });

        CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor)
                .whenComplete((v, t) -> {
                    Optional.of(v).ifPresent(System.out::println);
                    finished.set(true);
                });

        System.out.println("====i am no ---block----");
/*        while (!finished.get()) {
            Thread.sleep(1);
        }*/
    }
}
