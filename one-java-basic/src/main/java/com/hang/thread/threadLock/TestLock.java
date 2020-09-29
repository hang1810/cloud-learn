package com.hang.thread.threadLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hang
 * @date 2020-09-29 21:12
 */
public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 =new TestLock2();

        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }
}
class TestLock2 implements Runnable{

    int tickeNums = 10;

    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                if (tickeNums>0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.tickeNums--);
                }else {
                    break;
                }
            }finally {
                lock.unlock();
            }

        }
    }
}
