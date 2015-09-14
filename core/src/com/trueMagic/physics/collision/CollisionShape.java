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
public interface CollisionShape {
    public Shape getShape();
    public CollisionShape setCollisionPosition(Vector2 point);
    public boolean contains(Vector2 point);
    public boolean collision(CollisionShape shape);
}
