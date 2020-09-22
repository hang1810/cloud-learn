package com.hang.designpatterns.abstract_factory.demo1;

import com.hang.designpatterns.abstract_factory.demo1.factory.AbstractCothingFactory;
import com.hang.designpatterns.abstract_factory.demo1.factory.FactoryProducer;
import com.hang.designpatterns.abstract_factory.demo1.product.SpringCothing;
import com.hang.designpatterns.abstract_factory.demo1.product.WinterCothing;

/**
 * @author Hang
 * @date 2020-09-18 10:27
 */
public class AbstractFactoryMain {

    public static void main(String[] args) {
        //1.获取春装工厂
        AbstractCothingFactory springCothingFactory = FactoryProducer.getFactory("SPRING");

        //2.获取“童装，春装”对象
        SpringCothing childrenWearSpringCothing =
                springCothingFactory.getSpringCothing("CHILDER");

        //3.调具体方法，生产“童装，春装”产品
        childrenWearSpringCothing.productionClothing();

        //4.获取“青年装，春装”对象
        SpringCothing youtWearSpringCothing =
                springCothingFactory.getSpringCothing("YOUTH");

        //5.调具体方法，生产“青年装，春装”产品
        youtWearSpringCothing.productionClothing();

        //========================================================
        //1.获取冬装工厂
        AbstractCothingFactory winterCothingFactory  = FactoryProducer.getFactory("WINTER");

        //2.获取“童装，冬装”对象

        WinterCothing childerWearWinterCothing =
                winterCothingFactory.getWinterCothing("YOUTH");
        //3.生产“童装，冬装”
        childerWearWinterCothing.productionClothing();
    }
}
