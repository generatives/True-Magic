/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.physics.collision;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Declan Easton
 */
public class CollisionRectangle extends Rectangle implements CollisionShape {
    
    private Vector2 relativePosition;
    
    public CollisionRectangle(float x, float y, float width, float height, Vector2 relativePosition) {
        super(x, y, width, height);
        this.relativePosition = relativePosition;
    }
    
    public CollisionRectangle(CollisionRectangle rectangle, Vector2 relativePosition) {
        super(rectangle);
        this.relativePosition = relativePosition;
    }

    @Override
    public Shape getShape() {
        return Shape.RECTANGLE;
    }
    
    @Override
    public CollisionShape setCollisionPosition(Vector2 point) {
        super.setPosition(point.x + relativePosition.x, point.y + relativePosition.y);
        return this;
    }

    @Override
    public boolean collision(CollisionShape shape) {
        if(shape.getShape() == Shape.RECTANGLE) {
            return this.overlaps((CollisionRectangle) shape);
        }
        if(shape.getShape() == Shape.CIRCLE) {
            CollisionCircle circle = (CollisionCircle) shape;
            Vector2 distance = new Vector2(Math.abs(circle.x - (this.x + this.getWidth() / 2)), Math.abs(circle.y - (this.y + this.getHeight() / 2)));

            if (distance.x > (this.width/2 + circle.radius)) { return false; }
            if (distance.y > (this.height/2 + circle.radius)) { return false; }

            if (distance.x <= (this.width/2)) { return true; } 
            if (distance.y <= (this.height/2)) { return true; }

            float cornerDistance_sq = (distance.x - this.width/2) * (distance.x - this.width/2) +
                    (distance.y - this.getHeight() / 2) * (distance.y - this.getHeight() / 2);

            return (cornerDistance_sq <= (circle.radius * circle.radius));
        }
        return false;
    }
}
