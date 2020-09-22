package com.hang.designpatterns.abstract_factory.demo1.product;

/**
 * @author Hang
 * @date 2020-09-18 10:15
 */
public class YouthWearWinterCothing implements WinterCothing {
    @Override
    public void productionClothing() {
        System.out.println("冬装，青年装");
    }
}
