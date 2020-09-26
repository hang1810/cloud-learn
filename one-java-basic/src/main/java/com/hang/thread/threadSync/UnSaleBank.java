package com.hang.thread.threadSync;

/**
 * @author Hang
 * @date 2020-09-26 21:36
 */
public class UnSaleBank {
    public static void main(String[] args) {
        //账户
        Account account = new Account(100, "BANK");
        Drawing you = new Drawing(account, 50, "you");
        Drawing girlFriend = new Drawing(account, 100, "girlFriend");

        you.start();
        girlFriend.start();
//        Thread youThread = new Thread(you);
//        Thread girlFriendThread = new Thread(girlFriend);
//        youThread.start();
//        girlFriend.start();
    }
}
//账户
class Account{
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}
class Drawing extends Thread{
    Account account;

    int drawingMoney;//取了多少钱
    int nowMoney;//现在手里多少钱

    public Drawing( Account account, int drawingMoney,String name ) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;

    }

    //取钱
    @Override
    public void run() {
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