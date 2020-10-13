package com.hang.thread.threadCAS.ABA;

import java.util.concurrent.atomic.AtomicStampedReference;
/*
* 线程xx准备利用CAS修改变量值A，但是在修改之前，其他线程已经将A变成了B，然后又变成A，即A->B->A,
* 线程xx执行CAS的时候发现仍然为A，所以CAS会操作成功，但是其实目前这个A已经是其他线程修改的了，但是线程xx并不知道，最终内存值变成了B，这就导致了ABA问题。
* 引入AtomicStampedReference类解决aba问题
* */
/**
 * @author Hang
 * @date 2020-10-13 13:20
 */
public class AtomicStampedReferenceDemo {

    /**
     * 原子操作的aba 问题解决
     */
    private static AtomicStampedReference<Integer> atomicStampedReference;

    public static void main(String[] args) {
        for (int j=0;j<100;j++){
            for (int i=0;i<500;i++){
                atomicStampedReference =  new AtomicStampedReference<>(0,0);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<500;i++){
                            Integer reference;
                            int stamp;
                            do{
                                reference = atomicStampedReference.getReference();
                                stamp = atomicStampedReference.getStamp();
                            }while (!atomicStampedReference.compareAndSet(reference,reference+1,stamp,stamp+1));
                        }
                    }
                });

                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<500;i++){
                            Integer reference;
                            int stamp;
                            do{
                                reference = atomicStampedReference.getReference();
                                stamp = atomicStampedReference.getStamp();
                            }while (!atomicStampedReference.compareAndSet(reference,reference+1,stamp,stamp+1));
                        }
                    }
                });

                thread.start();
                thread2.start();
                try {
                    thread.join();
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicStampedReference.getReference() +"atomicStampedReference");
            }
        }
    }

}
