package com.yang.javabase.multithreading.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallerTest {

    public static class CallerTask implements Callable<String>{
        public String call() throws Exception {
            return "Hello";
        }
    }

    public static void main(String[] args) {

        FutureTask<String> futureTask = new FutureTask<String>(new CallerTask());

        new Thread(futureTask).start();

        try {
            String result = futureTask.get();
            if (result.equals("Hello")){
                System.out.println("执行完成");
            }

        }catch (RuntimeException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
