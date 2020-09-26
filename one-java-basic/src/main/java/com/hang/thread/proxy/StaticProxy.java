package com.hang.thread.proxy;

/**
 * @author Hang
 * @date 2020-09-26 14:29
 */
/*
* 静态代理模式总结
* 真实对象和代理对象都要实现同一个接口
* 代理对象要代理真实角色
*/
public class StaticProxy {
    public static void main(String[] args) {
        You you = new You();//真实对象

        new Thread( ()-> System.out.println("需要处理的事情，结婚") ).start();//线程

        new WeddingCompany(you).HappyMarry();//静态代理模式

//        WeddingCompany weddingCompany = new WeddingCompany(you);
//        weddingCompany.HappyMarry();
    }
}

interface Marry{
    void HappyMarry();

}
//正式角色 You marry
class You implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("Hang is Marry");
    }
}
//代理角色 帮助结婚
class WeddingCompany implements Marry{

    //代理谁--》真实目标角色
    private Marry marryPeople;

    //传入真实目标角色
    public WeddingCompany(Marry marryPeople) {
        this.marryPeople = marryPeople;
    }

    @Override
    public void HappyMarry() {
        before();
        this.marryPeople.HappyMarry();//这就是真实对象
        after();
    }

    public void before(){
        System.out.println("marry before");
    }

    public void after(){
        System.out.println("marry after");
    }
}
