package com.trueMagic.utils.math.shape;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public class Rectangle extends Shape {
    public float width, height;

    @Override
    public int getType() {
        return Shape.RECTANGLE;
    }
}
