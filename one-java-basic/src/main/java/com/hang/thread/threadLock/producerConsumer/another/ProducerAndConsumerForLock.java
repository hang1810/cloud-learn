package com.hang.thread.threadLock.producerConsumer.another;

/**
 * @author Hang
 * @date 2020-10-03 15:12
 */
/**
 * Lock 版本解决生产者消费者
 * await() / signal()方法
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumerForLock {
    // 1. 先定义最大生产长度
    private final int MAX_SIZE = 10;
    // 2.定义储存队列
    private Queue<Object> storeList = new LinkedList<Object>();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    class Producer extends Thread {
        @Override
        public void run() {
            producer();
        }

        private void producer() {
            while (true) {
                lock.lock();
                try {
                    while (storeList.size() == MAX_SIZE) {
                        System.out.println("当前库存满");
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    storeList.add(1);
                    condition.signal();
                    System.out.println("生产者产量增加一条，库存" + storeList.size());
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            consumer();
        }

        private void consumer() {
            while (true) {
                lock.lock();
                try {
                    while (storeList.size() == 0) {
                        System.out.println("当前库存为空");
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    storeList.poll();
                    condition.signal();
                    System.out.println("消费者消费一条任务，当前队列长度为" + storeList.size());
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumerForLock pc = new ProducerAndConsumerForLock();
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
