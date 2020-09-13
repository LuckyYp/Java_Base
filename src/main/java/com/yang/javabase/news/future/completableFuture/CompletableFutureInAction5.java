package com.yang.javabase.news.future.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

/**
 * 1.runAfterBoth,启用两个CompletableFuture 然后当这两个线程都执行完成后在执行统一的一个操作(例如:() -> System.out.println("done"))
 * 2.applyToEither ,启用两个CompletableFuture 然后当这两个线程中其中一个执行完成后就执行统一的一个操作例如:v -> v * 10,.他可以拿到先执行完成线程的执行结果
 * 3.acceptEither ,相对于applyToEither来说,acceptEither 不会把acceptEither方法中的结果(例如:applyToEither中的v -> v * 10)传递下去,其他和applyToEither一致
 * 4.runAfterEither 相对于acceptEither来说,runAfterEither获取不到执行完成的线程的执行结果值(不管先执行的还是后执行的都不能拿到结果),但是先执行的线程会运行写好的方法(也就是例如:System.out::println的这段代码),其他和applyToEither一致
 * CompletableFuture的静态方法
 *  allOf,对CompletableFuture数组中的全部CompletableFuture进行判断,当全部执行完成后进行一个操作  例如:.thenRun(() -> System.out.println("done"));
 *  anyOf 对CompletableFuture数组中的全部CompletableFuture进行判断,当任意一个执行完成后进行一个操作 例如:.thenRun(() -> System.out.println("done"));
 */
public class CompletableFutureInAction5 {
    public static void main(String[] args) throws InterruptedException {
        /*
        // 1.runAfterBoth使用
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            try {
                // 睡眠一会,如果没有使用 Thread.currentThread().join();,使用这条语句主要让主线程等他其他子线程执行完成,因为其他子线程是守护线程
                那么这条线程不会执行全部完成,会导致() -> System.out.println("done")这个语句打印不了
                // 因为他需要等待CompletableFuture 1 和 CompletableFuture 2执行完成才会运行

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            return 2;
        }), () -> System.out.println("done"));
         */

        /*
        // applyToEither 的使用
       CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return CompletableFutureInAction1.get();
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 2");
            return CompletableFutureInAction1.get();
        }), v -> v * 10)
                .thenAccept(System.out::println);
         */

        /*
        // acceptEither 的使用
        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return CompletableFutureInAction1.get();
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 2");
            return CompletableFutureInAction1.get();
        }), System.out::println);*/

       /*
       //runAfterEither 的使用
       CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return CompletableFutureInAction1.get();
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 2");
            return CompletableFutureInAction1.get();
        }), () -> System.out.println("done."));*/



       List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4)
                .stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::get))
                .collect(toList());

        CompletableFuture.anyOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> System.out.println("done"));

        CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> System.out.println("done"));


        //使用这条语句主要让主线程等他其他子线程执行完成,因为其他子线程是守护线程
       Thread.currentThread().join();
    }
}
