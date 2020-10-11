package com.hang.thread.threadLock.producerConsumer.semaphore;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
/**
 * @author Hang
 * @date 2020-10-03 15:47
 */
public class SemaphoreMain {
    public static void main(String[] args) {
        SemaphoreStorage semaphoreStorage = new SemaphoreStorage();
        Thread p1 = new Thread(new SemaphoreProducer(semaphoreStorage),"生产者1号");
        Thread p2 = new Thread(new SemaphoreProducer(semaphoreStorage),"生产者2号");
        Thread p3 = new Thread(new SemaphoreProducer(semaphoreStorage),"生产者3号");

        Thread c1 = new Thread(new SemaphoreConsumer(semaphoreStorage),"消费者1号");
        Thread c2 = new Thread(new SemaphoreConsumer(semaphoreStorage),"消费者2号");
        Thread c3 = new Thread(new SemaphoreConsumer(semaphoreStorage),"消费者3号");

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
 class SemaphoreStorage {
    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();
    // 仓库的最大容量
    final Semaphore notFull = new Semaphore(10);
    // 将线程挂起，等待其他来触发
    final Semaphore notEmpty = new Semaphore(0);
    // 互斥锁
    final Semaphore mutex = new Semaphore(1);

    public void produce()
    {
        try {
            notFull.acquire();
            mutex.acquire();
            list.add(new Object());
            System.out.println("【生产者" + Thread.currentThread().getName()
                    + "】生产一个产品，现库存" + list.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            notEmpty.release();
        }
    }

    public void consume()
    {
        try {
            notEmpty.acquire();
            mutex.acquire();
            list.remove();
            System.out.println("【消费者" + Thread.currentThread().getName()
                    + "】消费一个产品，现库存" + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            notFull.release();
        }
    }
}
class SemaphoreConsumer implements Runnable {
    private  SemaphoreStorage semaphoreStorage;

    public SemaphoreConsumer(){};
    public SemaphoreConsumer(SemaphoreStorage semaphoreStorage){
        this.semaphoreStorage = semaphoreStorage;
    }
    @Override
    public void run() {
        while (true){
            try {
                semaphoreStorage.consume();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class SemaphoreProducer implements Runnable {
    private  SemaphoreStorage semaphoreStorage;

    public SemaphoreProducer(){};
    public SemaphoreProducer(SemaphoreStorage semaphoreStorage){
        this.semaphoreStorage = semaphoreStorage;
    }
    @Override
    public void run() {
        while (true){
            try {
                semaphoreStorage.produce();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
