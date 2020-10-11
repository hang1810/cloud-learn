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
                waitNotifyStorage.consume();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
