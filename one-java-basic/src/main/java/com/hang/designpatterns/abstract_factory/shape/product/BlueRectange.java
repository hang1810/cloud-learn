package com.hang.designpatterns.abstract_factory.shape.product;

/**
 * 蓝色具体颜色的Rectange实现
 * @author Hang
 * @date 2020-09-18 11:31
 */
public class BlueRectange extends Rectange {
    @Override
    public void draw() {
        System.out.println("绘制蓝色长方形");
    }
}
