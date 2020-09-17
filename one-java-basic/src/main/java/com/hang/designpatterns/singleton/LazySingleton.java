package com.hang.designpatterns.singleton;

/** 懒汉模式
 * @author Hang
 * @date 2020-09-17 23:59
 */
public class LazySingleton {
    private static LazySingleton lazySingleton;

    //构造方法私有化
    private LazySingleton(){

    }
    //如果已经创建则直接返回，若没有创建则new返回
    //注意：多线程模式下懒汉模式无法保证单例
    public static LazySingleton getLazySingleton(){
        if (lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
