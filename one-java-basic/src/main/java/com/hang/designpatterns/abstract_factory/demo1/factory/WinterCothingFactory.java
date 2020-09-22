package com.hang.designpatterns.abstract_factory.demo1.factory;

import com.hang.designpatterns.abstract_factory.demo1.product.*;

/**
 * 冬装工厂类
 * @author Hang
 * @date 2020-09-18 10:24
 */

public class WinterCothingFactory extends AbstractCothingFactory {

    @Override
    public SpringCothing getSpringCothing(String springWearType) {
        return null;
    }

    @Override
    public WinterCothing getWinterCothing(String winterWearType) {
        if(null == winterWearType) {
            return null;
        }
        if("CHILDER".equalsIgnoreCase(winterWearType)){
            return  new ChilderWearWinterCothing();
        }
        if("YOUTH".equalsIgnoreCase(winterWearType)){
            return  new YouthWearWinterCothing();
        }
        if("SENIOR".equalsIgnoreCase(winterWearType)){
            return  new SeniorWearWinterCothing();
        }
        return null;
    }
}
