package com.hang.thread.threadCreate.thread;

/**
 * 线程创建通过继承Thread类，重写run方法
 * @author Hang
 * @date 2020-09-25 15:53
 */
public class MyThread extends Thread {

    @Override
    public void run() {
       // run方法线程体
        for (int i=0; i<10 ;i++){
            System.out.println("我在学Thread");
        }
    }

    public static void main(String[] args) {
        //main线程，主线程


        MyThread myThread = new MyThread();
        myThread.start();//start()，主线程和子线程并行交替执行
     // myThread.run();//run()，只有主线程在跑


        for (int j=0; j<10 ;j++){
            System.out.println("愿你一切安好");
        }
    }
}
