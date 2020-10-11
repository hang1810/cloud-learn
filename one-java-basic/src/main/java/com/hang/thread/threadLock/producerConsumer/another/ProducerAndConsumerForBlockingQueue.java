package com.hang.thread.threadLock.producerConsumer.another;

/**
 * @author Hang
 * @date 2020-10-03 15:42
 */
/**
 * BlockingQueue阻塞队列版本解决生产者消费者
 * put() / take() 方法
 */
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerAndConsumerForBlockingQueue {
    // 定义储存队列
    private LinkedBlockingQueue<Object> storeList = new LinkedBlockingQueue<>(10);

    class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        public void produce() {
            while (true) {
                try {
                    storeList.put(new Object());
                    System.out.println("生产者生产了一个商品 ,库存：" + storeList.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        public void consume() {
            while (true) {
                try {
                    storeList.take();
                    System.out.println("消费者消费了一个商品 ,库存：" + storeList.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumerForBlockingQueue pc = new ProducerAndConsumerForBlockingQueue();
        Producer producer = pc.new Producer();
        Consumer consumer = pc.new Consumer();
        producer.start();
        consumer.start();
    }

}