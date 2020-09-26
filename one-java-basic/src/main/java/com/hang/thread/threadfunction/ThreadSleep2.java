package com.hang.thread.threadfunction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author Hang
 * @date 2020-09-26 15:47
 * 线程休眠
 */
public class ThreadSleep2  {

    public static void tenDown(){
        int num =10;
        while (num>=0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(num);
            num --;
        }
    }

    public static void main(String[] args){
        tenDown();

        //打印系统当前时间
        Date startTime = new Date(System.currentTimeMillis());//获取系统当前时间

        while (true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
                startTime =  new Date(System.currentTimeMillis());//更新当前时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
