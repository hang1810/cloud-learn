package com.hang.thread.threadCreate.threadtest;

/**
 * 多个线程实例同时执行一个线程
 * @author Hang
 * @date 2020-09-25 16:55
 */
public class ThreadForAnybody implements Runnable {

    private int tickeNums =10;
    @Override
    public void run() {
        while (true){
            if (tickeNums<=0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+tickeNums+"张票");
            tickeNums--;
        }
    }

    public static void main(String[] args) {
        ThreadForAnybody threadForAnybody = new ThreadForAnybody();
        new Thread(threadForAnybody,"hang").start();
        new Thread(threadForAnybody,"Tom").start();
        new Thread(threadForAnybody,"Jerry").start();
    }
}
