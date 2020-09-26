package com.hang.thread.threadCreate.runnable;

/**
 * 线程创建通过实现Runnable接口，重写run方法,执行线程需要丢入runable接口实现类，调用start()
 * @author Hang
 * @date 2020-09-25 15:53
 */
public class MyThreadByRunnable implements Runnable {

    @Override
    public void run() {
       // run方法线程体
        for (int i=0; i<1000 ;i++){
            System.out.println("我在学Thread");
        }
    }

    public static void main(String[] args) {

        //创建runnable接口实现类
        MyThreadByRunnable myThreadByRunnable = new MyThreadByRunnable();
        //创建线程对象，通过线程对象来开启我们的线程MyThreadByRunnable，代理
        Thread thread = new Thread(myThreadByRunnable);
        thread.start();

//        new Thread(myThreadByRunnable).start();
        for (int j=0; j<1000 ;j++){
            System.out.println("愿你一切安好");
        }
    }
}
