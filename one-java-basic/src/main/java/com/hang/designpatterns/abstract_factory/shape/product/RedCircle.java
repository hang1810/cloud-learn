package com.hang.designpatterns.abstract_factory.shape.product;

/**
 * 红色具体颜色的Circle实现
 * @author Hang
 * @date 2020-09-18 11:30
 */
public class RedCircle extends Circle {
    @Override
    public void draw() {
        System.out.println("绘制红色的圆");
    }
}
