package com.hang.designpatterns.abstract_factory.shape.product;

/**
 * 蓝色具体颜色的Circle实现
 * @author Hang
 * @date 2020-09-18 11:29
 */
public class BlueCircle extends Circle {
    @Override
    public void draw() {
        System.out.println("绘制蓝色的圆");
    }
}
