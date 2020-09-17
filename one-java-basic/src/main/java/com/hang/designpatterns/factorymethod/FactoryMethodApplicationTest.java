package com.hang.designpatterns.factorymethod;

/**
 * @author Hang
 * @date 2020-09-16 22:45
 */
public class FactoryMethodApplicationTest {
    public static void main(String[] args) {
        Application application = new ConcreateProductA();
        Product product = application.getObject();
        product.method1();
    }
}
