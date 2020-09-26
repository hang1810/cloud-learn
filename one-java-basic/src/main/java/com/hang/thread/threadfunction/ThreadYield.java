package com.hang.thread.threadfunction;

/**
 * @author Hang
 * @date 2020-09-26 16:49
 * 线程礼让
 */
public class ThreadYield  {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield,"A").start();
        new Thread(myYield,"B").start();
    }
}

class MyYield implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"线程执行结束");

    }
}
