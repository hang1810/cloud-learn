package com.hang.thread.threadCreate.threadtest;

/**
 * 龟兔赛跑
 * @author Hang
 * @date 2020-09-25 17:04
 */
public class RaceTest implements Runnable {

    //胜利者
    private String winner;

    @Override
    public void run() {
        for (int i=0; i<=100 ;i++){

            //模拟兔子休息
            if (Thread.currentThread().getName().equals("兔子")&&i%10==0){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //判断比赛是否结束
            boolean flag = gameOver(i);
            //如果比赛结束，停止程序
            if (flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"-->跑了"+i+"米");
        }
    }

    private boolean gameOver(int steps){
        if (winner!=null){
            return true;
        }else {
            if (steps>=100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is "+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RaceTest raceTest = new RaceTest();
        new Thread(raceTest,"兔子").start();
        new Thread(raceTest,"乌龟").start();
    }
}
