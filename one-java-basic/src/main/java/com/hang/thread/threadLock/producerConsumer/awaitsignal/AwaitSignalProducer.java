package com.hang.thread.threadLock.producerConsumer.awaitsignal;
/**
 * @author Hang
 * @date 2020-09-29 22:29
 */
public class AwaitSignalProducer implements Runnable {

    private AwaitSignalStorage awaitSignalStorage;

    public AwaitSignalProducer(){};

    public AwaitSignalProducer(AwaitSignalStorage awaitSignalStorage){
        this.awaitSignalStorage = awaitSignalStorage;
    }

    @Override
    public void run() {
            while (true){
                try {
                    awaitSignalStorage.produce();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
