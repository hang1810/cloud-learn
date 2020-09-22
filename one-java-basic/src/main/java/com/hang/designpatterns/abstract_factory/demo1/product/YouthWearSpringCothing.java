package com.hang.designpatterns.abstract_factory.demo1.product;

/**
 * 青年装，春装
 * @author Hang
 * @date 2020-09-18 10:13
 */

public class YouthWearSpringCothing implements SpringCothing {

    /**
     * 重写方法
     */
    @Override
    public void productionClothing() {
        System.out.println("青年装，春装");
    }
}
