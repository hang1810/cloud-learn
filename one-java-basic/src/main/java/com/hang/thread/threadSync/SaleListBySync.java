package com.hang.thread.threadSync;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hang
 * @date 2020-09-27 20:16
 * 不安全的集合通过sync 来保证安全
 */
public class SaleListBySync {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                synchronized (list){
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());//正确为10000
    }

}
