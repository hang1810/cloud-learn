package com.hang.thread.threadSync;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hang
 * @date 2020-09-26 22:00
 * 不安全的集合
 */
public class UnSaleList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());//正确为10000
    }
}
