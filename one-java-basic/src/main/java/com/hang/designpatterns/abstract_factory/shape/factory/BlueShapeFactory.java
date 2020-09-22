package com.hang.designpatterns.abstract_factory.shape.factory;

import com.hang.designpatterns.abstract_factory.shape.product.BlueCircle;
import com.hang.designpatterns.abstract_factory.shape.product.BlueRectange;
import com.hang.designpatterns.abstract_factory.shape.product.Shape;

/**
 * 他所代表的是蓝色形状这一族
 * @author Hang
 * @date 2020-09-18 11:34
 */
public class BlueShapeFactory implements ShapeFactory {
    @Override
    public Shape getCircle() {
        return new BlueCircle();
    }

    @Override
    public Shape getRectange() {
        return new BlueRectange();
    }
}
