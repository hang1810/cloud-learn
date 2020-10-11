package com.hang.thread.threadLock.producerConsumer.waitNotify;

import java.util.LinkedList;

/**
 * @author Hang
 * @date 2020-09-29 22:22
 * 1. wait() / notify()方法
 * 当缓冲区已满时，生产者线程停止执行，放弃锁，使自己处于等状态，让其他线程执行；
 * 当缓冲区已空时，消费者线程停止执行，放弃锁，使自己处于等状态，让其他线程执行。
 *
 * 当生产者向缓冲区放入一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态；
 * 当消费者从缓冲区取出一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。
 */
public class WaitNotifyStorage {
    //仓库容量
    private final int MAX_SIZE = 10;
    //仓库的存储的载体
    private LinkedList<Object> list = new LinkedList<>();

    public void product(){
        synchronized (list){
            while (list.size() == MAX_SIZE){//超出最大容量退出
                System.out.println("【生产者" + Thread.currentThread().getName() + "】当前库存数量达到最大值：" + MAX_SIZE + "条");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list.add(new Object());
            list.notifyAll();
            System.out.println("【生产者" + Thread.currentThread().getName()  + "】生产一个产品，现库存" + list.size());
        }
    }

    public void consume(){
        synchronized (list){
            while (list.size()==0){//没库存了
                System.out.println("【消费者" + Thread.currentThread().getName()  + "】仓库为空");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove();
            list.notifyAll();
            System.out.println("【消费者" + Thread.currentThread().getName()  + "】消费一个产品，现库存" + list.size());
        }
    }
}
