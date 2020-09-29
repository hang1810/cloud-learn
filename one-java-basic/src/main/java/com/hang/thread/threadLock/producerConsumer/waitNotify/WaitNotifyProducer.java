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
                    Thread.sleep(1000);
                    waitNotifyStorage.product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
