package com.yang.javabase.multithreading.Thread;


public class WaitTest {

    private static volatile Integer count = 0;
    private static final Integer MAX_NUM = 5;
    private static final Integer MIN_NUM = 0;

    private static void take(){

        synchronized (count){

            try {
                if(count >= MAX_NUM){
                    count.wait();
                }
            }catch (RuntimeException | InterruptedException e){
                e.printStackTrace();
            }

            count++;

            System.out.println("add" + count);
            // 唤醒挂起的消费者线程
            count.notifyAll();
        }

    }

    private static void get(){

        synchronized (count){

            try {
                if(count <= MIN_NUM){
                    count.wait();
                }
            }catch (RuntimeException | InterruptedException e){
                e.printStackTrace();
            }
            count--;
            System.out.println("get" + count);
            // 唤醒挂起的消费者线程
            count.notifyAll();
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    take();
                }
            }).start();
        }



    }


}
