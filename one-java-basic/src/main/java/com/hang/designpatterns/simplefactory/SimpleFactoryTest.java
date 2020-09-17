package com.hang.designpatterns.simplefactory;

/**
 * 简单工厂模式
 * @author Hang
 * @date 2020-09-15 23:29
 */
public class SimpleFactoryTest {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Fruit apple = FruitSimpleFactory.getFruit("Apple");
        apple.get();

        Fruit banana = FruitSimpleFactory.getFruit("Banana");
        banana.get();

        //FruitSimpleFactory 采用Class.ForName写法，type需要写路径
        Fruit apple2 = FruitSimpleFactory.getFruit2("com.hang.designpatterns.simplefactory.Apple");
        apple2.get();
    }
}
