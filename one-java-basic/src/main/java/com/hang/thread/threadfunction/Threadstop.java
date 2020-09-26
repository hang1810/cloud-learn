package com.hang.thread.threadfunction;

/**
 * @author Hang
 * @date 2020-09-26 15:32
 *
 * 线程停止
 * 1、建议线程正常停止--->利用次数，不建议死循环
 * 2、建议使用标志位--->设置一个标志位
 * 3、不要使用stopdestory等过时方法
 */

public class Threadstop implements Runnable {
    //1、设置标志位
    private boolean flag=true;

    @Override
    public void run() {
        int  num = 0;
        while (flag){
            System.out.println("run--Thread  "+num++);
        }
    }

    //2、设置一个公开的方法停止线程，转换标志位
    public void stop(){
        this.flag=false;
    }

    public static void main(String[] args) {
        Threadstop threadstop = new Threadstop();
         new Thread(threadstop).start();

         for (int i=0;i<1000;i++){
             System.out.println("main in "+ i);
             if (i==900){
                 //3、转换标志位
                 threadstop.stop();
                 System.out.println("stop--Thread" );
             }
         }
    }
}
