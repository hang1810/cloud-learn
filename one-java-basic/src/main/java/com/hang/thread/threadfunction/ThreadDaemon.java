package com.hang.thread.threadfunction;

/**
 * @author Hang
 * @date 2020-09-26 21:04
 * 守护线程
 */
public class ThreadDaemon {
    public static void main(String[] args) {
        You you = new You();
        Earth earth = new Earth();


        Thread earthThread = new Thread(earth);
        earthThread.setDaemon(true);//守护线程，默认false表现用户线程，正常的线程都是用户线程

        earthThread.start();
        new Thread(you).start();


    }
}
class Earth  implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("Earth in ");
        }
    }
}
class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("Happy Everyday , I LOVE YOU");
        }
        System.out.println("GOODBYE , Nice to meet you");
    }
}
