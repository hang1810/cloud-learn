package com.hang.thread.threadLock.producerConsumer.awaitsignal;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hang
 * @date 2020-10-02 13:40
 */
public class AwaitSignalStorage {
    //仓库容量
    private final int MAX_SIZE = 10;
    //仓库的存储的载体
    private LinkedList<Object> list = new LinkedList<>();
    // 锁
    private final Lock lock = new ReentrantLock();
    // 仓库满的条件变量
    private final Condition full = lock.newCondition();
    // 仓库空的条件变量
    private final Condition empty = lock.newCondition();

    public void produce(){
        //获得锁
        lock.lock();
        try{
            while (list.size()==MAX_SIZE){
                System.out.println("【生产者" + Thread.currentThread().getName() + "】仓库已满");
                try {
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(new Object());
            empty.signal();
            System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + list.size());
        }finally {
            lock.unlock();
        }
    }

    public void consume(){
        //获得锁
        lock.lock();
        try {
            while (list.size() == 0) {
                try {
                    System.out.println("【消费者" + Thread.currentThread().getName() + "】仓库已空");
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.poll();
            System.out.println("【消费者" + Thread.currentThread().getName() + "】消费一个产品，现库存" + list.size());
            full.signal();
        }finally {
            lock.unlock();
        }
    }
}
