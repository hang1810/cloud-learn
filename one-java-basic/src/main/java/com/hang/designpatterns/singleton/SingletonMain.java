package com.hang.designpatterns.singleton;

/**
 * @author Hang
 * @date 2020-09-17 23:47
 */
public class SingletonMain {
    public static void main(String[] args) {


        //单例模式--饿汉模式
        HungrySingleton hungrySingleton = HungrySingleton.getPersonSingleton();
        HungrySingleton hungrySingleton1 = HungrySingleton.getPersonSingleton();
        System.out.println(hungrySingleton == hungrySingleton1);

        //单例模式--懒汉模式
        LazySingleton lazySingleton = LazySingleton.getLazySingleton();
        LazySingleton lazySingleton1 =LazySingleton.getLazySingleton();
        System.out.println(lazySingleton == lazySingleton1);

        //单例模式--加锁懒汉模式
        SyncLazySingleton syncLazySingleton = SyncLazySingleton.getLazySingleton();
        SyncLazySingleton syncLazySingleton1 = SyncLazySingleton.getLazySingleton();
        System.out.println(syncLazySingleton == syncLazySingleton1);

        //单例模式--双重验证加锁懒汉模式
        DoubleSyncLazySingleton doubleSyncLazySingleton = DoubleSyncLazySingleton.getLazySingleton();
        DoubleSyncLazySingleton doubleSyncLazySingleton1 = DoubleSyncLazySingleton.getLazySingleton();
        System.out.println(doubleSyncLazySingleton == doubleSyncLazySingleton1);


        //静态内部类
        SingletonStaticInnerClass singletonStaticInnerClass = SingletonStaticInnerClass.getSingletonStaticInnerClass();
        SingletonStaticInnerClass singletonStaticInnerClass1 = SingletonStaticInnerClass.getSingletonStaticInnerClass();
        System.out.println(singletonStaticInnerClass==singletonStaticInnerClass1);

    }
}
