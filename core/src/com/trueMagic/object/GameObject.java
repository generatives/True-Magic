/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.trueMagic.scripting.ExecutableScript;
import com.trueMagic.world.TrueMagicWorld;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.Contact;

/**
 *
 * @author Declan Easton
 */
public class GameObject {
    
    protected Sprite[] sprites;
    
    protected float timeAdded;
    protected int index;
    protected TrueMagicWorld worldManager;
    
    protected Body body;
    
    protected ExecutableScript[] behaviors;
    
    public GameObject(TrueMagicWorld worldManager, BodyDef bodyDef, FixtureDef[] fixtures) {
        sprites = new Sprite[5];
        
        this.worldManager = worldManager;
        
        behaviors = new ExecutableScript[5];
        
        body = worldManager.getPhysicsWorld().createBody(bodyDef);
        for(FixtureDef fixture : fixtures) {
            body.createFixture(fixture);
        }
        
        body.setUserData(this);
    }
    
    public void startContact(Contact contact, GameObject otherObject) {
        
    }

    public void endContact(Contact contact, GameObject otherObject) {

    }
        
    public void gameTick(float deltaTime) {
        
        for (ExecutableScript behavior : behaviors) {
            if (behavior != null) {
                try {
                    behavior.execute();
                }catch (ScriptException ex) {
                    Logger.getLogger(GameObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void gameTickFinished(float deltaTime) {
        
    }
    
    public void draw(Batch batch, ShapeRenderer shapeRenderer) {
        for(int i = 0; i < sprites.length; i++) {
            if(sprites[i] != null) {
                sprites[i].setX((body.getPosition().x * 64 - sprites[i].getWidth() / 2));
                sprites[i].setY((body.getPosition().y * 64 - sprites[i].getHeight() / 2));
                sprites[i].setRotation((float) (body.getAngle() * 360 / (2 * Math.PI)));
                sprites[i].draw(batch);
            }
        }
    }
    
    public void remove() {
        worldManager.getPhysicsWorld().destroyBody(body);
    }
    
    public void setTimeAdded(float time) {
        timeAdded = time;
    }
    
    public float getTimeAdded() {
        return timeAdded;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }

    /**
     * @return the body
     */
    public Body getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Body body) {
        this.body = body;
    }
    
    public TrueMagicWorld getWorldManager() {
        return worldManager;
    }
    
    public boolean addBehavior(ExecutableScript behavior) {
        for(int i = 0; i < behaviors.length; i++) {
            if(behaviors[i] != null) {
                behaviors[i] = behavior;
                behavior.setGameObject(this);
                return true;
            }
        }
        return false;
    }
    
    public void kill() {
        this.remove();
    }
}