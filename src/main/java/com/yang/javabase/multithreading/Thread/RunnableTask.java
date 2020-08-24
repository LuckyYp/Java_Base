package com.yang.javabase.multithreading.Thread;

public class RunnableTask {


    // 实现Runnable接口，1. 实现代码和任务分离 2.解决单继承的缺点
    public static class RunnableTest implements Runnable{

        public void run() {
            System.out.println(" implements Runnable");
        }
    }

    public static void main(String[] args) {

        RunnableTest task = new RunnableTest();

        new Thread(task).start();

        new Thread(task).start();

    }

}
