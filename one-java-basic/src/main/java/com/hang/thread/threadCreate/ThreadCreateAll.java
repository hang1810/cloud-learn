package com.hang.thread.threadCreate;

import java.util.concurrent.*;

/**
 * @author Hang
 * @date 2020-10-02 13:23
 * 线程创建总结
 */
public class ThreadCreateAll {
    public static void main(String[] args) {
        Integer integer = 0;

        //继承Thread
        new Thread1().start();

        //实现RUNNABLE
        new Thread(new Thread2()).start();

        //实现Callable
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Thread3());
        new Thread(futureTask).start();
        try {
            integer = futureTask.get();
            System.out.println(integer);



        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result1 = executorService.submit(new Thread3());
        integer=integer+result1.get();
        System.out.println(integer);

        executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class Thread1 extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"Thread1");
    }
}
class Thread2 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"Thread2");

    }
}
class Thread3 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"Thread3");
        return 100;
    }
}