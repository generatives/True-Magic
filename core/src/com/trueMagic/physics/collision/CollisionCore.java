/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.physics.collision;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Declan Easton
 */
public class CollisionCore {
    
    public boolean collide(CollisionCircle circle, CollisionRectangle rectangle) {
        Vector2 distance = new Vector2(Math.abs(circle.x - (rectangle.x + rectangle.getWidth() / 2)), Math.abs(circle.y - (rectangle.y + rectangle.getHeight() / 2)));

        if (distance.x > (rectangle.width/2 + circle.radius)) { return false; }
        if (distance.y > (rectangle.height/2 + circle.radius)) { return false; }

        if (distance.x <= (rectangle.width/2)) { return true; } 
        if (distance.y <= (rectangle.height/2)) { return true; }

        float cornerDistance_sq = (distance.x - rectangle.width/2) * (distance.x - rectangle.width/2) +
                (distance.y - rectangle.getHeight() / 2) * (distance.y - rectangle.getHeight() / 2);

        return (cornerDistance_sq <= (circle.radius * circle.radius));
    }
    
    public boolean collide(CollisionCircle circle1, CollisionCircle circle2) {
        return circle1.overlaps(circle2);
    }
    
    public boolean collide(CollisionRectangle rectangle1, CollisionRectangle rectangle2) {
        return rectangle1.overlaps(rectangle2);
    }
}
