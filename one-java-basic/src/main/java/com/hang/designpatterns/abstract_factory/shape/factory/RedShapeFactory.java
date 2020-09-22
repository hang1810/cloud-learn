package com.hang.designpatterns.abstract_factory.shape.factory;

import com.hang.designpatterns.abstract_factory.shape.product.RedCircle;
import com.hang.designpatterns.abstract_factory.shape.product.RedRectange;
import com.hang.designpatterns.abstract_factory.shape.product.Shape;

/**
 * RedShapeFactory（他所代表的是红色形状这一族）
 * @author Hang
 * @date 2020-09-18 11:33
 */
public class RedShapeFactory  implements ShapeFactory {


    @Override
    public Shape getCircle() {
        return new RedCircle();
    }

    @Override
    public Shape getRectange() {
        return new RedRectange();
    }
}
