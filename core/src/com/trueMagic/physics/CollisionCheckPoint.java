/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.physics;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Declan Easton
 */
public class CollisionCheckPoint {
    
    private Vector2 relativePosition;
    private Vector2 position;
    private boolean colliding;
    
    public CollisionCheckPoint(Vector2 relativePosition, boolean colliding) {
        this.position = new Vector2();
        this.relativePosition = relativePosition;
        this.colliding = colliding;
    }

    /**
     * @return the point
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * @param point the point to set
     */
    public void setPosition(Vector2 parentPosition) {
        this.position.set(relativePosition.x + parentPosition.x, relativePosition.y + parentPosition.y);
    }

    /**
     * @return the collides
     */
    public boolean isColliding() {
        return colliding;
    }

    /**
     * @param collides the collides to set
     */
    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }
    
    
}
