package com.hang.designpatterns.abstract_factory.shape.product;

/**
 * 红色具体颜色的Rectange实现
 * @author Hang
 * @date 2020-09-18 11:31
 */
public class RedRectange extends Rectange {
    @Override
    public void draw() {
        System.out.println("绘制红色长方形");
    }
}
