package com.hang.thread.threadLock.producerConsumer.waitNotify;

/**
 * @author Hang
 * @date 2020-09-29 22:33
 */
public class WaitNotifyMain {
    public static void main(String[] args) {
        WaitNotifyStorage storage = new WaitNotifyStorage();
        Thread p1 = new Thread(new WaitNotifyProducer(storage),"生产者1号");
        Thread p2 = new Thread(new WaitNotifyProducer(storage),"生产者2号");
        Thread p3 = new Thread(new WaitNotifyProducer(storage),"生产者3号");

        Thread c1 = new Thread(new WaitNotifyConsumer(storage),"消费者1号");
        Thread c2 = new Thread(new WaitNotifyConsumer(storage),"消费者2号");
        Thread c3 = new Thread(new WaitNotifyConsumer(storage),"消费者3号");

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
