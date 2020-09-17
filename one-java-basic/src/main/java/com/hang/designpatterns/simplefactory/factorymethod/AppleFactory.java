package com.hang.designpatterns.simplefactory.factorymethod;

/**
 * @author Hang
 * @date 2020-09-16 23:07
 */
public class AppleFactory implements FruitFactory{
    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
