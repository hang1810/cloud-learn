package com.hang.thread.threadfunction;

/**
 * @author Hang
 * @date 2020-09-26 16:55
 */
public class ThreadJoin implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        ThreadJoin threadJoin = new ThreadJoin();
        Thread thread = new Thread(threadJoin);
        thread.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("main当前位数:"+i);
            if (i==100){
                thread.join();
            }
        }


    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程vip来了，vin位数"+i);
        }
    }


}
