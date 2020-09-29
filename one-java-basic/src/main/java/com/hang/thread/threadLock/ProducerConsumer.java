package com.hang.thread.threadLock;

/**
 * @author Hang
 * @date 2020-09-29 21:32
 * 生产者 消费者-->缓冲区处理：管程法
 */

public class ProducerConsumer {
    public static void main(String[] args) {
        SyncContainer syncContainer = new SyncContainer();
        new Producer(syncContainer).start();
        new Consumer(syncContainer).start();
    }
}

//生产者
class Producer extends  Thread{
    SyncContainer syncContainer;

    public Producer(SyncContainer syncContainer){
        this.syncContainer = syncContainer;
    }
    //生成
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {

            try {
                Thread.sleep(1000);
                syncContainer.push(new Chicken(i));
                System.out.println("生产了编号为："+i+" 的鸡");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//消费者
class Consumer extends  Thread{
    SyncContainer syncContainer;

    public Consumer(SyncContainer syncContainer){
        this.syncContainer = syncContainer;
    }
    //消费
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            try {
                Thread.sleep(2000);
                System.out.println("消费了编号为："+syncContainer.pop().id+" 的鸡");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
//产品
class Chicken {
    int id ;//产品编号
    public Chicken(int id){
        this.id = id;
    }
}

//缓冲区
class SyncContainer{
    //需要一个容器
    Chicken[] chickens = new Chicken[5];
    int count = 0;

    //生产者放入
    public synchronized void push(Chicken chicken){

        //如果容器满了。需要等待消费者消费
        while (count == chickens.length){
            //通知消费者消费，停止生产
            try {
                System.out.println("容器满了。需要等待消费者消费");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果容器空，需要生产者生产放入
        chickens[count] = chicken;
        count++;
        System.out.println("容器："+count);
        this.notifyAll();
    }
    //消费者取出

    public synchronized Chicken pop(){
        //如果容器空了

        while (count == 0){
            //通知生产者生产
            try {
                System.out.println("容器空了。需要等待生产者生产");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //消费
        count--;
        Chicken chicken=chickens[count];

        //消费了，通知生产者继续生产
        this.notifyAll();
        System.out.println("容器："+count);
        return chicken;
    }
}