package com.hang.designpatterns.singleton;

/**
 * 静态内部类实现单例模式
 * @author Hang
 * @date 2020-09-18 10:09
 */

public class SingletonStaticInnerClass {

    /**
     * 静态内部类 InnerClass
     * 静态内部类的一个特点是：不能从静态内部类的对象中访问非静态外部类的对象
     */
    private static class InnerClass{
        private static final SingletonStaticInnerClass singletonStaticInnerClass =
                new SingletonStaticInnerClass();
    }

    /**
     * 私有构造方法
     */
    private SingletonStaticInnerClass(){}

    public static final SingletonStaticInnerClass getSingletonStaticInnerClass(){
        return InnerClass.singletonStaticInnerClass;
    }

    public void method(){
        System.out.println("静态内部类:这是单例类的一个普通方法");
    }

}
