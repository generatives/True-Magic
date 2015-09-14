package com.trueMagic.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.trueMagic.utils.math.shape.Shape;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public class DrawShape extends Component {
    public Shape shape;
    public Color color;
    public ShapeRenderer.ShapeType shapeType;
}
