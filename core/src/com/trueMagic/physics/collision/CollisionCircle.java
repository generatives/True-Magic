/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.physics.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Declan Easton
 */
public class CollisionCircle extends Circle implements CollisionShape {
    
    private Vector2 relativePosition;
    
    public CollisionCircle(float x, float y, float radius) {
        super(x, y, radius);
        this.relativePosition = relativePosition;
    }
    
    public CollisionCircle(CollisionCircle circle) {
        super(circle);
        this.relativePosition = relativePosition;
    }
    
    public CollisionShape setCollisionPosition(Vector2 point) {
        super.setPosition(point.x + relativePosition.x, point.y + relativePosition.y);
        return this;
    }

    @Override
    public Shape getShape() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean collision(CollisionShape shape) {
        if(shape.getShape() == Shape.CIRCLE) {
            return this.overlaps((CollisionCircle) shape);
        }
        if(shape.getShape() == Shape.RECTANGLE) {
            CollisionRectangle rectangle = (CollisionRectangle) shape;
            Vector2 distance = new Vector2(Math.abs(this.x - (rectangle.x + rectangle.getWidth() / 2)), Math.abs(this.y - (rectangle.y + rectangle.getHeight() / 2)));

            if (distance.x > (rectangle.width/2 + this.radius)) { return false; }
            if (distance.y > (rectangle.height/2 + this.radius)) { return false; }

            if (distance.x <= (rectangle.width/2)) { return true; } 
            if (distance.y <= (rectangle.height/2)) { return true; }

            float cornerDistance_sq = (distance.x - rectangle.width/2) * (distance.x - rectangle.width/2) +
                    (distance.y - rectangle.getHeight() / 2) * (distance.y - rectangle.getHeight() / 2);

            return (cornerDistance_sq <= (this.radius * this.radius));
        }
        return false;
    }
    
}
