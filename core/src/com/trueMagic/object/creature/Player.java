/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.object.creature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.trueMagic.game.screens.MainGame;
import com.trueMagic.scripting.api.apiObjects.ISpellCaster;
import com.trueMagic.textureGeneration.TextureGenerator;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;

/**
 *
 * @author Declan Easton
 */
public class Player extends Creature implements ISpellCaster {
    
    protected float energyPool;
    protected final float maxEnergy = 100;
    
    public Player(MainGame game, Vector2 position) {
        super(game, TextureGenerator.player(32, 96), PlayerFactory.playerBodyDef(position), PlayerFactory.playerFixtureDef(), 15, 10, 40);
        this.groundSensor = this.body.getFixtureList();
        energyPool = maxEnergy;
    }
    
    @Override
    public void gameTick(float deltaTime) {
        
        
        energyPool += deltaTime * 5;
        if(energyPool > maxEnergy) {
            energyPool = maxEnergy;
        }
        
        Vector2 mousePosition = this.worldManager.getGame().unprojectVector(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        mousePosition.scl(1f / 64f);
        
        if(mousePosition.x > this.body.getPosition().x) {
            facingRight = true;
        } else {
            facingRight = false;
        }
        
        super.gameTick(deltaTime);
    }
    
    @Override
    public void draw(Batch batch, ShapeRenderer shapeRenderer) {
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        Vec2 position = this.body.getPosition();
//        shapeRenderer.setColor(Color.BLUE);
//        shapeRenderer.rect((position.x * 64) - 16, (position.y * 64) - 48, 32, 96);
//        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        Fixture fixture = this.body.m_fixtureList;
        while(fixture != null) {
            if(fixture.getType() == ShapeType.POLYGON) {
                PolygonShape shape = (PolygonShape)fixture.getShape();
                Vec2[] verts = shape.getVertices();
                for (int i = 1; i < shape.m_count; i++) {
                    shapeRenderer.line((verts[i - 1].x + this.body.getPosition().x) * 64,
                            (verts[i - 1].y + this.body.getPosition().y) * 64,
                            (verts[i].x + this.body.getPosition().x) * 64,
                            (verts[i].y + this.body.getPosition().y) * 64);
                }
            }
            fixture = fixture.getNext();
        }
        shapeRenderer.end();

    }

    @Override
    public boolean drawEnergy(float energy) {
        energy = 0;
        energyPool -= energy;
        if(energyPool < 0) {
            energyPool = 0;
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Vector2 getCastingPosition() {
        if(facingRight) {
            return new Vector2(body.getPosition().x + 0.25f, body.getPosition().y - 0.5f);
        } else {
            return new Vector2(body.getPosition().x - 0.25f, body.getPosition().y - 0.5f);
        }
    }
    
    public static class PlayerFactory {
        
        public static BodyDef playerBodyDef(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(position.x, position.y);
        bodyDef.fixedRotation = true;
        
        return bodyDef;
    }
    
    public static FixtureDef[] playerFixtureDef() {
        
        PolygonShape mainShape = new PolygonShape();
        mainShape.setAsBox(0.25f, 0.725f, new Vec2(0f, 0.025f), 0);
        
        FixtureDef mainFixtureDef = new FixtureDef();
        mainFixtureDef.shape = mainShape;
        mainFixtureDef.density = 8f;
        mainFixtureDef.friction = 0.1f;
        mainFixtureDef.restitution = 0f;

        PolygonShape footShape = new PolygonShape();
        footShape.setAsBox(0.2f, 0.025f, new Vec2(0f, -0.725f), 0);

        FixtureDef footFixtureDef = new FixtureDef();
        footFixtureDef.shape = footShape;
        footFixtureDef.density = 8f;
        footFixtureDef.friction = 2f;
        footFixtureDef.restitution = 0f;
        
        FixtureDef[] fixtures = {mainFixtureDef, footFixtureDef};
        return fixtures;
    }
    }
    
}