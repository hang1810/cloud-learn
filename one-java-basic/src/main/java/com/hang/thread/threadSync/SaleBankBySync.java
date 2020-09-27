package com.hang.thread.threadSync;

/**
 * @author Hang
 * @date 2020-09-27 20:22
 */
public class SaleBankBySync {
    public static void main(String[] args) {
        //账户
        Account account = new Account(100, "BANK");
        DrawingSync you = new DrawingSync(account, 50, "you");
        DrawingSync girlFriend = new DrawingSync(account, 100, "girlFriend");

        you.start();
        girlFriend.start();
//        Thread youThread = new Thread(you);
//        Thread girlFriendThread = new Thread(girlFriend);
//        youThread.start();
//        girlFriend.start();
    }
}
//账户

class DrawingSync extends Thread{
    Account account;

    int drawingMoney;//取了多少钱
    int nowMoney;//现在手里多少钱

    public DrawingSync( Account account, int drawingMoney,String name ) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;

    }

    //取钱
    @Override
    public void run() {
        synchronized(account){
            //判断有没有钱
            if (account.money-drawingMoney<0){
                System.out.println(Thread.currentThread().getName()+"钱不够");
                return;
            }

            //sleep 放大问题
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //卡里余额的钱
            account.money = account.money-drawingMoney;
            //你手里的钱
            nowMoney = nowMoney + drawingMoney;

            System.out.println(account.name+"余额为："+account.money);
            //Thread.currentThread().getName()  = this.name
            System.out.println(this.getName()+"手里的钱："+this.nowMoney);
        }
    }

}
