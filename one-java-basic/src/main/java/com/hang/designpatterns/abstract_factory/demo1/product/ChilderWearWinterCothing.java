package com.hang.designpatterns.abstract_factory.demo1.product;

/**
 * @author Hang
 * @date 2020-09-18 10:14
 */
public class ChilderWearWinterCothing implements WinterCothing {
    @Override
    public void productionClothing() {
        System.out.println("冬装，童装");
    }
}
