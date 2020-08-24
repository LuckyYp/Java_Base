package com.yang.javabase.multithreading.Thread;

public class ThreadTest {

    // 继承Thread类
    public static class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println("extends Thread");
        }
    }

    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.start();

    }
}
