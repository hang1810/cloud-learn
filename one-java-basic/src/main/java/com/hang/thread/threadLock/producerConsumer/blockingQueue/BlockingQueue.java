package com.hang.thread.threadLock.producerConsumer.blockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Hang
 * @date 2020-10-03 15:33
 */
public class BlockingQueue {
    public static void main(String[] args) {
        BlockingQueueStorage blockingQueueStorage = new BlockingQueueStorage();
        Thread p1 = new Thread(new BlockingQueueProducer(blockingQueueStorage),"生产者1号");
        Thread p2 = new Thread(new BlockingQueueProducer(blockingQueueStorage),"生产者2号");
        Thread p3 = new Thread(new BlockingQueueProducer(blockingQueueStorage),"生产者3号");

        Thread c1 = new Thread(new BlockingQueueConsumer(blockingQueueStorage),"消费者1号");
        Thread c2 = new Thread(new BlockingQueueConsumer(blockingQueueStorage),"消费者2号");
        Thread c3 = new Thread(new BlockingQueueConsumer(blockingQueueStorage),"消费者3号");

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
class BlockingQueueStorage {

    // 仓库存储的载体
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<>(10);

    public void produce() {
        try{
            list.put(new Object());
            System.out.println("【生产者" + Thread.currentThread().getName()
                    + "】生产一个产品，现库存" + list.size());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void consume() {
        try{
            list.take();
            System.out.println("【消费者" + Thread.currentThread().getName()
                    + "】消费了一个产品，现库存" + list.size());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class BlockingQueueConsumer implements Runnable {
    private  BlockingQueueStorage blockingQueueStorage;

    public BlockingQueueConsumer(){};
    public BlockingQueueConsumer(BlockingQueueStorage blockingQueueStorage){
        this.blockingQueueStorage = blockingQueueStorage;
    }
    @Override
    public void run() {
        while (true){
            try {
                blockingQueueStorage.consume();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class BlockingQueueProducer implements Runnable {
    private  BlockingQueueStorage blockingQueueStorage;

    public BlockingQueueProducer(){};
    public BlockingQueueProducer(BlockingQueueStorage blockingQueueStorage){
        this.blockingQueueStorage = blockingQueueStorage;
    }
    @Override
    public void run() {
        while (true){
            try {
                blockingQueueStorage.produce();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
