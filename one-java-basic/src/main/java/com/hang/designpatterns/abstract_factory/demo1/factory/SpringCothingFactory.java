package com.hang.designpatterns.abstract_factory.demo1.factory;

import com.hang.designpatterns.abstract_factory.demo1.product.*;

/**
 * 春装工厂类
 * @author Hang
 * @date 2020-09-18 10:22
 */

public class SpringCothingFactory extends  AbstractCothingFactory {

    @Override
    public SpringCothing getSpringCothing(String springWearType) {
        if(null == springWearType) {
            return null;
        }
        if("CHILDER".equalsIgnoreCase(springWearType)){
            return  new ChildrenWearSpringCothing();
        }
        if("YOUTH".equalsIgnoreCase(springWearType)){
            return  new YouthWearSpringCothing();
        }
        if("SENIOR".equalsIgnoreCase(springWearType)){
            return  new SeniorWearSpringCothing();
        }
        return null;
    }

    @Override
    public WinterCothing getWinterCothing(String winterWearType) {
        return null;
    }
}
