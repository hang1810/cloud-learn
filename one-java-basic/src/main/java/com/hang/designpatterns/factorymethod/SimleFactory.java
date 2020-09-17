package com.hang.designpatterns.factorymethod;

/**
 * @author Hang
 * @date 2020-09-16 22:56
 */
public class SimleFactory {
    public static Product createProduct(String type) {
        if (type == null) {
            System.out.println("类型为空，无法返回实例化类");
            return null;
        } else if (type.equals("ProductA")) {
            return new ProductA();
        } else if (type.equals("ProductB")) {
            return new ProductB();//  return SimpleFactoryTest.Banana.class.newInstance();
        } else {
            System.out.println("找不到相应的实例化类");
            return null;
        }
    }
}