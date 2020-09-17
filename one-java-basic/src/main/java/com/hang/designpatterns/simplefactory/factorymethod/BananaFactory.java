package com.hang.designpatterns.simplefactory.factorymethod;

/**
 * @author Hang
 * @date 2020-09-16 23:08
 */
public class BananaFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Banana();
    }
}
