package com.hang.thread.threadLock.producerConsumer.waitNotify;

/**
 * @author Hang
 * @date 2020-09-29 22:29
 */
public class WaitNotifyProducer implements Runnable {

    private WaitNotifyStorage waitNotifyStorage;

    public WaitNotifyProducer(){};

    public WaitNotifyProducer(WaitNotifyStorage waitNotifyStorage){
        this.waitNotifyStorage = waitNotifyStorage;
    }

    @Override
    public void run() {
            while (true){
                try {
                    waitNotifyStorage.product();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
