package com.hang.thread.threadCAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Hang
 * @date 2020-10-13 9:31
 */
public class Demo1_ListCAS {
    public volatile static  int count = 0;
    public volatile static  int countSynchronized = 0;
    public static AtomicInteger countAtomicInteger = new AtomicInteger();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            //非线程安全
                            for (int i1 = 0; i1 < 10000; i1++) {
                                count++;
                            }
                            //synchronized来实现线程安全
                            for (int i1 = 0; i1 < 10000; i1++) {
                                synchronized (Demo1_ListCAS.class){
                                    countSynchronized++;
                                }
                            }
                            //CAS AtomicInteger 实现线程安全
                            for (int i1 = 0; i1 < 10000; i1++) {
                               countAtomicInteger.incrementAndGet();
                            }
                        }
                    }
            ){}.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count="+count);
        System.out.println("countSynchronized="+countSynchronized);
        System.out.println("countAtomicInteger="+countAtomicInteger.get());
    }
}
