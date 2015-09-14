/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.object.spell;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.trueMagic.object.GameObject;
import com.trueMagic.object.GameObjectFactory;
import com.trueMagic.textureGeneration.TextureGenerator;
import com.trueMagic.world.TrueMagicWorld;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Declan Easton
 */
public class Projectile extends GameObject {

    public Projectile(TrueMagicWorld worldManager, Vector2 position, Vector2 velocity) {
        super(worldManager, GameObjectFactory.basicProjectileBodyType(position), GameObjectFactory.basicProjectileFixtureDef());
        this.body.setLinearVelocity(new Vec2(velocity.x, velocity.y));
    }
    
    public Projectile(TrueMagicWorld worldManager, Vector2 position) {
        super(worldManager, GameObjectFactory.basicProjectileBodyType(position), GameObjectFactory.basicProjectileFixtureDef());
        this.sprites[0] = new Sprite(TextureGenerator.projectile(16, 16));
    }
}
