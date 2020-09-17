package com.hang.designpatterns.factorymethod;

/**
 * @author Hang
 * @date 2020-09-16 22:58
 */
public class ConcreateProductA extends Application{

    @Override
    Product createProduct() {
        return new ProductA();
    }
}
