package com.hang.thread.threadLock;

/**
 * @author Hang
 * @date 2020-09-29 20:56
 * 死锁：多个线程相互需要对方的资源，形成僵持
 */
public class Deadlock {
    public static void main(String[] args) {
        MakeUp girl1 = new MakeUp(0,"Tom" );
        MakeUp girl2 = new MakeUp(1,"Jerry" );
        girl1.start();
        girl2.start();
    }
}
//口红
class Lipstick{

}
//镜子
class Mirror{

}
class MakeUp extends Thread{

    //需要的资源 only one  通过static 保证只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;//选择
    String girlName;//使用者

    MakeUp(int choice,String girlName){
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        //化妆
        try {
            makeUp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //多个线程相互需要对方的资源，形成僵持
    private void makeUp() throws InterruptedException {
        if (choice==0){
            synchronized (lipstick){
                System.out.println(this.girlName+"获得口红的锁");
                Thread.sleep(100);
                synchronized (mirror){
                    System.out.println(this.girlName+"获得镜子的锁");
                }
            }
        }else {
            synchronized (mirror){
                System.out.println(this.girlName+"获得镜子的锁");
                Thread.sleep(200);
                synchronized (lipstick){
                    System.out.println(this.girlName+"获得口红的锁");
                }
            }
        }
    }
}