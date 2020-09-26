package com.hang.thread.threadCreate.threadtest;

/**
 * @author Hang
 * @date 2020-09-25 22:42
 */
public class RunTest {
    public static void main(String[] args) {
        new RunTest().runTest();
    }
    private String winner;
    private boolean flag = false;
    public void gameOver(){
        if (!flag){
            flag = true;
            winner = Thread.currentThread().getName();
        }else {
            System.out.println("winner is "+winner);
        }
    }
    public void runTest() {
        Thread t1 = new Thread("小乌龟") {
            public void run() {
                int count = 0;
                while (count < 100) {
                    if (flag){
                        break;
                    }
                    try {
                        //方便在控制台上查看
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count += 5;
                    //每10米打印一次
                    if (count % 10 == 0) {
                        System.out.println("小乌龟跑了" + count + "米");
                    }
                }
                gameOver();
            }
        };

        Thread t2 = new Thread("小兔子") {
            public void run() {

                int count = 0;
                while (count < 100) {
                    if (flag){
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    count += 10;
                    if (count % 10 == 0) {
                        System.out.println("小兔子跑了" + count + "米");
                    }
                    //到70米停两秒
                    if (count == 70) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                gameOver();

            }
        };
        t1.start();
        t2.start();
     }
}
