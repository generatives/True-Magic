/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.object;

import com.badlogic.gdx.math.Vector2;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 *
 * @author Declan Easton
 */
public class GameObjectFactory {
    
    public static BodyDef basicProjectileBodyType(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(position.x, position.y);
        bodyDef.fixedRotation = false;
        
        return bodyDef;
    }
    
    public static FixtureDef[] basicProjectileFixtureDef() {
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(10 / 2 / 64, 10 / 2 / 64);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = poly;
        fixtureDef.density = 5f; 
        fixtureDef.friction = 2f;
        fixtureDef.restitution = 0f;
        FixtureDef[] fixtures = {fixtureDef};
        return fixtures;
    }
}
