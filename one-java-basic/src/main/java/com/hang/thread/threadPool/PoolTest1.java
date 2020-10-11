package com.hang.thread.threadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hang
 * @date 2020-10-02 13:15
 */
public class PoolTest1 {
    public static void main(String[] args) {
        //创建服务，创建线程池
        //newFixedThreadPool 参数为线程的大小
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //执行2
        executorService.execute(new ThreadPoolTest1());
        executorService.execute(new ThreadPoolTest1());
        executorService.execute(new ThreadPoolTest1());
        executorService.execute(new ThreadPoolTest1());

        //关闭服务
        executorService.shutdown();
    }
}
class ThreadPoolTest1 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
