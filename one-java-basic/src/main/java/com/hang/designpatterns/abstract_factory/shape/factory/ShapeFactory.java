package com.hang.designpatterns.abstract_factory.shape.factory;

import com.hang.designpatterns.abstract_factory.shape.product.Shape;

/**
 * @author Hang
 * @date 2020-09-18 11:32
 */
public interface ShapeFactory {
    Shape getCircle();
    Shape getRectange();
}
