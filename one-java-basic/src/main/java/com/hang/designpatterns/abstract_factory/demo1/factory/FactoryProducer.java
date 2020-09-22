package com.hang.designpatterns.abstract_factory.demo1.factory;

/**
 * @author Hang
 * @date 2020-09-18 10:26
 */
/**
 * 工厂生成器类
 */
public class FactoryProducer {

    public static AbstractCothingFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SPRING")){
            return new SpringCothingFactory();
        } else if(choice.equalsIgnoreCase("WINTER")){
            return new WinterCothingFactory();
        }
        return null;
    }
}
