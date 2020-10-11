package com.hang.thread.threadLock.producerConsumer.awaitsignal;

import com.hang.thread.threadLock.producerConsumer.waitNotify.WaitNotifyConsumer;
import com.hang.thread.threadLock.producerConsumer.waitNotify.WaitNotifyProducer;
import com.hang.thread.threadLock.producerConsumer.waitNotify.WaitNotifyStorage;

/**
 * @author Hang
 * @date 2020-10-02 13:40
 */
public class AwaitSignalMain {
    public static void main(String[] args) {
        AwaitSignalStorage storage = new AwaitSignalStorage();
        Thread p1 = new Thread(new AwaitSignalProducer(storage),"生产者1号");
        Thread p2 = new Thread(new AwaitSignalProducer(storage),"生产者2号");
        Thread p3 = new Thread(new AwaitSignalProducer(storage),"生产者3号");

        Thread c1 = new Thread(new AwaitSignalConsumer(storage),"消费者1号");
        Thread c2 = new Thread(new AwaitSignalConsumer(storage),"消费者2号");
        Thread c3 = new Thread(new AwaitSignalConsumer(storage),"消费者3号");

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
