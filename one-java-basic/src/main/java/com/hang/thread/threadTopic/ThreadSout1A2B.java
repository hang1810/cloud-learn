package com.hang.thread.threadTopic;

/**
 * @author Hang
 * @date 2020-09-29 23:55
 *
 * 双线程轮流打印
 * 线程1负责打印a,b,c,d
 * 线程2负责打印1,2,3,4
 *
 * 思路： A 线程先执行，拿到锁，wait()等待，释放锁
 *       B 线程执行，等A释放完锁，B拿到锁，开始执行，notify()叫醒A,让A等待准备执行，B接着wait()等待，释放锁
 *       A 执行，拿到锁，开始执行，打印num,notify()叫醒B,让B等待准备执行，A接着wait()等待，释放锁
 *       B 线程执行，等A释放完锁，B拿到锁，开始执行，打印Letters, notify()叫醒A,让A等待准备执行，B接着wait()等待，释放锁
 */
public class ThreadSout1A2B {

    public static void main(String[] args) {
        Cache cache = new Cache();

        Thread nums = new Thread(new Nums(cache));
        Thread letters = new Thread(new Letters(cache));

        nums.start();
        letters.start();


    }
}

class Cache {}
class Nums implements Runnable{
    Cache cache;
    public Nums(Cache cache){
        this.cache = cache;
    }
    String nums[] = {"1","2","3","4"};
    @Override
    public void run() {
        for (int i=0;i<nums.length;i++){
            synchronized (cache){
                System.out.println("线程Nums拿到锁，开始执行");

                try {
                    System.out.println("线程Nums开始等待，释放锁");
                    cache.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程Nums开始打印");
                System.out.println(nums[i]);
                System.out.println("线程Nums打印结束，叫醒letters");
                cache.notify();
            };
        }
    }
}
class Letters implements Runnable{
    Cache cache;
    public Letters(Cache cache){
        this.cache = cache;
    }
    String letters[] = {"a","b","c","d"};
    @Override
    public void run() {
        for (int i=0;i<letters.length;i++){
            synchronized (cache){
                System.out.println("线程letters拿到锁，开始执行，叫醒nums");
                cache.notify();
                try {
                    System.out.println("线程letters开始等待，释放锁");
                    cache.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程letters开始打印");
                System.out.println(letters[i]);
                System.out.println("线程letters打印结束");

            };
        }
    }
}
