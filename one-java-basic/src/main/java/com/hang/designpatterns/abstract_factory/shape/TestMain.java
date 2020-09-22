package com.hang.designpatterns.abstract_factory.shape;

import com.hang.designpatterns.abstract_factory.shape.factory.BlueShapeFactory;
import com.hang.designpatterns.abstract_factory.shape.factory.RedShapeFactory;
import com.hang.designpatterns.abstract_factory.shape.factory.ShapeFactory;
import com.hang.designpatterns.abstract_factory.shape.product.Circle;
import com.hang.designpatterns.abstract_factory.shape.product.Shape;

/**
 * @author Hang
 * @date 2020-09-18 11:35
 */
public class TestMain {
    public static void main(String[] args) {
        ShapeFactory redShapeFactory = new RedShapeFactory();
        Shape redCircle = redShapeFactory.getCircle();
        redCircle.draw();

        Shape redtangle = redShapeFactory.getRectange();
        redtangle.draw();


        ShapeFactory buleShapeFactory = new BlueShapeFactory();
        Shape buleCircle = buleShapeFactory.getCircle();
        buleCircle.draw();

        Shape buletangle = buleShapeFactory.getRectange();
        buletangle.draw();
    }
}
