package com.hang.designpatterns.factorymethod;

/**
 * @author Hang
 * @date 2020-09-16 22:58
 */
public  abstract class Application{
    abstract  Product createProduct();

    Product getObject(){
        Product product = createProduct();
        // ...
        return product;
    }
}
