/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.object.creature;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.trueMagic.game.TrueMagic;
import com.trueMagic.object.GameObject;
import com.trueMagic.game.screens.MainGame;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.Contact;

/**
 *
 * @author Declan Easton
 */
public class Creature extends GameObject {
    
    protected MainGame game;
    
    protected Vec2 selfPropelledForce;
    protected Vec2 smallXForce;
    protected float maximumSelfPropelledVelocity;
    protected float selfPropelledDirection;
    protected Vec2 jumpForce;
    
    protected boolean facingRight;
    
    protected int moveButtonsPressed;

    protected Fixture groundSensor;
    protected int groundContacts;
    
    public Creature(MainGame game, Texture texture, BodyDef bodyDef, FixtureDef[] fixtures, float selfPropelledForce, float maximumSelfPropelledVelocity, float jumpForce) {
        super(game.world, bodyDef, fixtures);
        this.sprites[0] = new Sprite(texture);
        
        this.game = game;
        
        this.selfPropelledForce = new Vec2(selfPropelledForce, 0);
        this.smallXForce = new Vec2(1, 0);
        this.maximumSelfPropelledVelocity = maximumSelfPropelledVelocity;
        this.selfPropelledDirection = 0;
        this.jumpForce = new Vec2(0, jumpForce);
        
        this.moveButtonsPressed = 0;
    }
    
    @Override
    public void gameTick(float deltaTime) {
        super.gameTick(deltaTime);

        if(selfPropelledDirection != 0 && groundContacts > 0) {

            float xVel = body.getLinearVelocity().x;
            if(xVel <= maximumSelfPropelledVelocity && xVel >= -maximumSelfPropelledVelocity) {
                this.body.applyLinearImpulse(selfPropelledForce.mul(selfPropelledDirection), this.body.getPosition(), true);
            } else {
                if(xVel < 0) {
                    this.body.applyLinearImpulse(smallXForce, this.body.getPosition(), true);
                } else {
                    this.body.applyLinearImpulse(selfPropelledForce.mul(-1), this.body.getPosition(), true);
                }
            }
        }
    }
    
    @Override
    public void draw(Batch batch, ShapeRenderer shapeRenderer) {
        for (Sprite sprite : sprites) {
            if (sprite != null) {
                sprite.setX(body.getPosition().x * 64 - sprite.getWidth() / 2);
                sprite.setY(body.getPosition().y * 64 - sprite.getHeight() / 2);
                sprite.setRotation((float) (body.getAngle() * 360 / (2 * Math.PI)));
                sprite.flip(facingRight, false);
                sprite.draw(batch);
            }
        }
    }

    public void moveLeft() {
        selfPropelledDirection = -1;
        this.body.applyLinearImpulse(selfPropelledForce.mul(selfPropelledDirection), this.body.getPosition(), true);
        moveButtonsPressed++;
    }
    
    public void moveRight() {
        selfPropelledDirection = 1;
        this.body.applyLinearImpulse(selfPropelledForce.mul(selfPropelledDirection), this.body.getPosition(), true);
        moveButtonsPressed++;
    }
    
    public void moveDown() {
        this.body.applyLinearImpulse(new Vec2(0, -selfPropelledForce.x), this.body.getPosition(), true);
    }
    
    public void stopMove() {
        if(moveButtonsPressed > 0) {
            moveButtonsPressed--;
            if(moveButtonsPressed <= 0) {
               selfPropelledDirection = 0; 
            }
        } else {
            moveButtonsPressed = 0;
        }
    }
    
    public void jump() {
        if(groundContacts > 0) {
            this.body.applyLinearImpulse(jumpForce, this.body.getPosition(), true);
        }
    }

    @Override
    public void startContact(Contact cntct, GameObject obj) {
        super.startContact(cntct, obj);
        if(cntct.getFixtureA() == groundSensor ||
                cntct.getFixtureB() == groundSensor) {
            this.groundContacts++;
        }
    }

    @Override
    public void endContact(Contact cntct, GameObject obj) {
        super.startContact(cntct, obj);
        if(cntct.getFixtureA() == groundSensor ||
                cntct.getFixtureB() == groundSensor) {
            this.groundContacts--;
        }
    }
}
