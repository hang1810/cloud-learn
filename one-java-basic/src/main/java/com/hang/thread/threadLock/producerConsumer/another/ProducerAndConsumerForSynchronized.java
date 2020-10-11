package com.hang.thread.threadLock.producerConsumer.another;

/**
 * @author Hang
 * @date 2020-10-03 14:59
 */
/**
 * Synchronized 版本解决生产者消费者
 * wait() / notify()方法
 */
import java.util.LinkedList;
import java.util.Queue;

public class ProducerAndConsumerForSynchronized {
    // 1. 先定义最大生产长度
    private final int MAX_SIZE = 10;
    // 2.定义储存队列
    private Queue<Object> storeList = new LinkedList<Object>();

    // 3.定义生产者
    class Producer extends Thread {
        @Override
        public void run() {
            producer();
        }

        private void producer() {
            while (true) {
                synchronized (storeList) {
                    while (storeList.size() == MAX_SIZE) {
                        storeList.notify();
                        System.out.println("当前库存数量达到最大值：" + MAX_SIZE + "条");
                        try {
                            // 暂停生产
                            storeList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    storeList.add(new Object());
                    storeList.notify();
                    System.out.println("生产者产量增加一条，库存" + storeList.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 4.定义消費者
    class Consumer extends Thread {
        @Override
        public void run() {
            consumer();
        }

        private void consumer() {
            while (true) {
                synchronized (storeList) {
                    while (storeList.size() == 0) {
                        storeList.notify();
                        System.out.println("当前库存为空");
                        try {
                            // 暂停消费
                            storeList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    storeList.poll();
                    storeList.notify();
                    System.out.println("消费者消费一条产量，当前库存为" + storeList.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumerForSynchronized pc = new ProducerAndConsumerForSynchronized();
        Producer producer1 = pc.new Producer();
        Producer producer2 = pc.new Producer();
        Producer producer3 = pc.new Producer();
        Consumer consumer1 = pc.new Consumer();
        Consumer consumer2 = pc.new Consumer();
        Consumer consumer3 = pc.new Consumer();
        producer1.start();
        producer2.start();
        producer3.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }
}
