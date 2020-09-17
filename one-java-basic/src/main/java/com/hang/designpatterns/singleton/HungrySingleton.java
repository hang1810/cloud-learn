package com.hang.designpatterns.singleton;

/**
 * 单例模式--饿汉模式
 * @author Hang
 * @date 2020-09-17 23:45
 */
public class HungrySingleton {
    //不管有没有人来调用，直接先创建一个实例
    public static final HungrySingleton hungrySingleton = new HungrySingleton();
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //构造方法私有化
    private HungrySingleton(){

    }
    //提供全局静态方法
    public static HungrySingleton getPersonSingleton(){
        return hungrySingleton;
    }
}
