package com.hang.designpatterns.abstract_factory.demo1.product;

/**
 * 老年装，春装
 * @author Hang
 * @date 2020-09-18 10:14
 */

public class SeniorWearSpringCothing implements  SpringCothing {

    @Override
    public void productionClothing() {
        System.out.println("老年装，春装");
    }
}
