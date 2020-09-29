package com.hang.thread.threadLock.producerConsumer.waitNotify;

/**
 * @author Hang
 * @date 2020-09-29 22:32
 */
public class WaitNotifyConsumer implements Runnable {
    private  WaitNotifyStorage waitNotifyStorage;

    public WaitNotifyConsumer(){};
    public WaitNotifyConsumer(WaitNotifyStorage waitNotifyStorage){
        this.waitNotifyStorage = waitNotifyStorage;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(2000);
                waitNotifyStorage.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
