package com.hang.thread.threadfunction;

/**
 * @author Hang
 * @date 2020-09-26 15:47
 * 线程休眠
 */
public class ThreadSleep implements Runnable {

    private int tickeNums =10;
    @Override
    public void run() {
        while (true){
            if (tickeNums<=0){
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+ tickeNums--+"张票");

        }
    }

    public static void main(String[] args) {
        ThreadSleep threadSleep = new ThreadSleep();
        new Thread(threadSleep,"hang").start();
        new Thread(threadSleep,"Tom").start();
        new Thread(threadSleep,"Jerry").start();
    }
}
