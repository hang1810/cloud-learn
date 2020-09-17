package com.hang.designpatterns.factorymethod;

/**
 * @author Hang
 * @date 2020-09-16 22:58
 */
public class ConcreateProductB extends Application{

    @Override
    Product createProduct() {
        return new ProductB();
    }
}
