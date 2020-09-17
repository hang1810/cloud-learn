package com.hang.designpatterns.simplefactory.factorymethod;

/**
 * @author Hang
 * @date 2020-09-15 23:49
 */
public class Apple implements Fruit {
        /*
        *  采集
        */
        public void get(){
            System.out.println("采集Apple");
        }
        public void getName(){
            System.out.println("name is Apple");
        }

}
