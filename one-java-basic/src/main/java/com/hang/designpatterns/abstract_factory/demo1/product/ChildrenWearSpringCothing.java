package com.hang.designpatterns.abstract_factory.demo1.product;

/**
 * 春装，童装
 * @author Hang
 * @date 2020-09-18 10:13
 */

public class ChildrenWearSpringCothing implements SpringCothing{

    /**
     * 重写方法
     */
    @Override
    public void productionClothing() {
        System.out.println("春装，童装");
    }
}
