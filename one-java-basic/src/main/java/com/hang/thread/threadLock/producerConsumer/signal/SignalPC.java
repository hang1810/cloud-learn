package com.hang.thread.threadLock.producerConsumer.signal;

/**
 * @author Hang
 * @date 2020-10-02 12:47
 * 信号灯法
 */
public class SignalPC {
    public static void main(String[] args) {
        TV tv = new TV();
         new Player(tv).start();
         new Watcher(tv).start();
    }
}
//生产者-->演员
class Player extends  Thread{
    TV tv;
    public Player(TV tv){
        this.tv = tv;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i%2==0){
                this.tv.paly("舌尖上的中国播放中");
            }else{
                this.tv.paly("记录美好生活");
            }
        }
    }
}
//消费者 -- >观众
class Watcher extends Thread{
    TV tv;
    public Watcher(TV tv){
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}

//产品--> 节目
class TV{
    //演员表演，观众等待
    //观众观看，演员等待
    String voice;//表演的节目
    boolean flag = true;

    //表演
    public synchronized void paly(String voice){
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了："+voice);
        //通知观众观看
        this.notifyAll();//唤醒
        this.voice = voice;
        this.flag=!this.flag;
    }

    //观看
    public synchronized void watch(){
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("观看了："+this.voice);
        //通知观众观看
        this.notifyAll();//唤醒
        this.flag=!this.flag;
    }
}