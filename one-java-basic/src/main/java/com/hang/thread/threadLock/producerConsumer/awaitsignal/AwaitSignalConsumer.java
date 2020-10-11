package com.hang.thread.threadLock.producerConsumer.awaitsignal;

import com.hang.thread.threadLock.producerConsumer.waitNotify.WaitNotifyStorage;

/**
 * @author Hang
 * @date 2020-09-29 22:32
 */
public class AwaitSignalConsumer implements Runnable {
    private  AwaitSignalStorage awaitSignalStorage;

    public AwaitSignalConsumer(){};
    public AwaitSignalConsumer(AwaitSignalStorage awaitSignalStorage){
        this.awaitSignalStorage = awaitSignalStorage;
    }
    @Override
    public void run() {
        while (true){
            try {
                awaitSignalStorage.consume();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
