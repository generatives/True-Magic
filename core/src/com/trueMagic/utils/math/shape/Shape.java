package com.trueMagic.utils.math.shape;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public abstract class Shape {
    public static final int CIRCLE = 0;
    public static final int RECTANGLE = 1;

    public abstract int getType();
    public float x, y;
}
