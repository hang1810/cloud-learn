package com.hang.designpatterns.singleton;

/** 加锁懒汉模式
 * @author Hang
 * @date 2020-09-17 23:59
 */
public class SyncLazySingleton {
    private static SyncLazySingleton syncLazySingleton;

    //构造方法私有化
    private SyncLazySingleton(){

    }
    //如果已经创建则直接返回，若没有创建则new返回
    //注意：多线程模式下懒汉模式无法保证单例
    //升级：添加同步锁进行预防多线程模式下的情况
    // 加锁 会增加性能的消耗
    public static synchronized SyncLazySingleton getLazySingleton(){
        if (syncLazySingleton == null){
            syncLazySingleton = new SyncLazySingleton();
        }
        return syncLazySingleton;
    }
}
