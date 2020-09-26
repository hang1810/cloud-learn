package com.hang.thread.threadSync;

/**
 * @author Hang
 * @date 2020-09-26 21:26
 * 线程不安全--买票
 */
public class UnSafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        Thread t1 = new Thread(buyTicket,"hang");
        Thread t2 = new Thread(buyTicket,"Tome");
        Thread t3 = new Thread(buyTicket,"Jarry");

        t1.start();
        t2.start();
        t3.start();
    }
}
class BuyTicket implements Runnable {

    //票
    private int ticket=10;
    private boolean flag = true;
    @Override
    public void run() {
        while (flag){
            //买票
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void buy() throws InterruptedException {
        //判断是否有票
        if (ticket<=0){
            flag=false;
            return;
        }
        Thread.sleep(100);
        //买票
        System.out.println(Thread.currentThread().getName()+"买到第"+ticket--+"票");
    }
}
