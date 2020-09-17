package com.hang.designpatterns.simplefactory.factorymethod;

/**
 * 简单工厂模式 升级工厂方法模式
 * @author Hang
 * @date 2020-09-15 23:29
 */
public class SimpleFactoryTest {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        //获取苹果工厂FruitFactory
        FruitFactory appleFactory = new AppleFactory();
        //通过苹果工厂FruitFactory来获得苹果实例对象apple
        Fruit apple = appleFactory.getFruit();
        apple.get();

        //获取苹果工厂FruitFactory
        FruitFactory bananaFactory = new BananaFactory();
        //通过苹果工厂FruitFactory来获得苹果实例对象apple
        Fruit banana = bananaFactory.getFruit();
        apple.get();
    }
}
