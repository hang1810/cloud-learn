package com.hang.designpatterns.abstract_factory.demo1.factory;

import com.hang.designpatterns.abstract_factory.demo1.product.SpringCothing;
import com.hang.designpatterns.abstract_factory.demo1.product.WinterCothing;

/**
 * 工厂类
 * @author Hang
 * @date 2020-09-18 10:16
 */
public abstract class AbstractCothingFactory {
    public abstract SpringCothing getSpringCothing(String springWearType);
    public abstract WinterCothing getWinterCothing(String springWearType);

}
