package com.hang.designpatterns.singleton;

/** 双重验证加锁懒汉模式
 * @author Hang
 * @date 2020-09-17 23:59
 */
public class DoubleSyncLazySingleton {
    private static DoubleSyncLazySingleton doubleSyncLazySingleton;

    //构造方法私有化
    private DoubleSyncLazySingleton(){

    }
    //如果已经创建则直接返回，若没有创建则new返回
    //注意：多线程模式下懒汉模式无法保证单例
    //升级：添加同步锁进行预防多线程模式下的情况
    //加锁 会增加性能的消耗
    //双重验证 将锁放在判断上,只会锁一次  减少加锁性能的消耗
    public static synchronized DoubleSyncLazySingleton getLazySingleton(){
        if (doubleSyncLazySingleton == null){
            synchronized (DoubleSyncLazySingleton.class){
                if (doubleSyncLazySingleton == null){
                    doubleSyncLazySingleton = new DoubleSyncLazySingleton();
                }
            }
        }
        return doubleSyncLazySingleton;
    }
}
