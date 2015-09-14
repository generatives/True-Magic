/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.trueMagic.object.GameObject;
import com.trueMagic.object.creature.Creature;
import com.trueMagic.object.creature.Player;
import com.trueMagic.game.screens.MainGame;
import com.trueMagic.object.enviroment.EnviromentPoint;
import com.trueMagic.utils.collections.RegularGrid;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

/**
 *
 * @author Declan Easton
 */
public class TrueMagicWorld implements ContactListener {
    
    private MainGame game;
    
    private World physicsWorld;
    private Player player;
    private RegularGrid<EnviromentPoint> enviromentPoints;
    
    public final Vector2 gravity =  new Vector2(0, (float) 0);
    
    public TrueMagicWorld(MainGame game, int xSize, int ySize, int maxObjects) {
        this.game = game;
        physicsWorld = new World(new Vec2(0, -10));
        physicsWorld.setContactListener(this);
        
        enviromentPoints = new RegularGrid<EnviromentPoint>(0.2f);
    }
    
    public GameObject getGameObjectList() {
        return (GameObject) physicsWorld.getBodyList().getUserData();
    }
    
    public GameObject nextGameObject(GameObject object) {
        Body body = object.getBody().getNext();
        if(body != null) {
            return (GameObject) body.getUserData();
        } else {
            return null;
        }
    }
    
    public void step(float deltaTime) {
        physicsWorld.step(deltaTime, 6, 2);
        for(Body body = physicsWorld.getBodyList();body != null; body = body.getNext()) {
            ((GameObject)body.getUserData()).gameTick(deltaTime);
        }
        
    }
    
    public void stepFinished(float deltaTime) {
        for(Body body = physicsWorld.getBodyList();(body = body.getNext()) != null;) {
            ((GameObject)body.getUserData()).gameTickFinished(deltaTime);
        }
    }
    
    public void draw(Batch batch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        int xMax = enviromentPoints.xBound();
        int yMax = enviromentPoints.yBound();
        for(int x = 0; x < xMax; x++) {
            for(int y = 0; y < yMax; y++) {
                EnviromentPoint point = (EnviromentPoint) enviromentPoints.getIndex(x, y);
                if(point != null) {
                    float centerX = point.x * 64;
                    float centerY = point.y * 64;
                    //shapeRenderer.rect(centerX - 1, centerY - 1, 2, 2);
                    //shapeRenderer.circle(centerX, centerY, 1);
                }
            }
        }
        shapeRenderer.end();
        
        Body body = physicsWorld.getBodyList();
        do {
            ((GameObject)body.getUserData()).draw(batch, shapeRenderer);
        } while((body = body.getNext()) != null);
    }
    
    public void setEnviromentPoints(RegularGrid<EnviromentPoint> points) {
        this.enviromentPoints = points;
    }
    
    public void stepSimulation(float deltaTime) {
        physicsWorld.step(deltaTime, 6, 2);
    }
    
    public void spawnPlayer(float xCoord, float yCoord) {
        player = new Player(game, new Vector2(xCoord, yCoord));
    }
    
    public MainGame getGame() {
        return game;
    }

    /**
     * @return the physicsWorld
     */
    public World getPhysicsWorld() {
        return physicsWorld;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void beginContact(Contact cntct) {
        GameObject objectA  = (GameObject)cntct.getFixtureA().getBody().getUserData();
        GameObject objectB  = (GameObject)cntct.getFixtureB().getBody().getUserData();
        objectA.startContact(cntct, objectB);
        objectB.startContact(cntct, objectA);
    }

    @Override
    public void endContact(Contact cntct) {
        GameObject objectA  = (GameObject)cntct.getFixtureA().getBody().getUserData();
        GameObject objectB  = (GameObject)cntct.getFixtureB().getBody().getUserData();
        objectA.endContact(cntct, objectB);
        objectB.endContact(cntct, objectA);
    }

    @Override
    public void preSolve(Contact cntct, Manifold mnfld) {
        
    }

    @Override
    public void postSolve(Contact cntct, ContactImpulse ci) {
        
    }
}
