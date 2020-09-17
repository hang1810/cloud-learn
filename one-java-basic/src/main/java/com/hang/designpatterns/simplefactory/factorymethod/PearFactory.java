package com.hang.designpatterns.simplefactory.factorymethod;

/**
 * @author Hang
 * @date 2020-09-16 23:08
 */
public class PearFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Pear();
    }
}
