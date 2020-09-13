package com.yang.javabase.news.future.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * 1.thenApply
 * 2.handle (相对于thenApply,多了对异常的获取)
 * 3.thenRun 没有入参,也就是不会使用到运算结果的值
 * 4.thenAccept 可以获取到运算结果,但是不会传递下去(没有返回值,也就是对之前的运算结果操作后不能return回去给下一个操作使用 )
 * 5. thenCompose 将第一个CompletableFuture运算结果传递给另一个新的CompletableFuture进行运行,然后会分别获取到这两个CompletableFuture运算结果,并可以对他们进行操作
 *    也就是第二个CompletableFuture可以使用到第一个的CompletableFuture运算结果,并且对两个CompletableFuture运算结果的进一步操作可以传递下去
 *
 * 5.thenCombine 相当于于thenCompose 差别在于第一个CompletableFuture运算结果不会传递给另一个新的CompletableFuture,但仍然会分别获取到这两个CompletableFuture运算结果,并可以对他们进行操作,并将
 * 结果可以传递下去
 *
 * 6.thenAcceptBoth 和thenCombine类似,但不能将结果传递下去
 */
public class CompletableFutureInAction4 {

    public static void main(String[] args) throws InterruptedException {

        /*CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .whenComplete((v, t) -> System.out.println(v));*/

/*        CompletableFuture.supplyAsync(() -> 1)
                .handle((v, t) -> Integer.sum(v, 10))
                .whenComplete((v, t) -> System.out.println(v))
                .thenRun(System.out::println);
/*
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .thenAccept(System.out::println);*/

        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i))
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> r1 + r2)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> {
                    System.out.println(r1);
                    System.out.println(r2);
                    System.out.println(r1 + r2);
                });

        Thread.sleep(1000L);
    }
}
