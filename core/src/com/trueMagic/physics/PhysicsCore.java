///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.trueMagic.physics;
//
//import com.badlogic.gdx.math.Vector2;
//import com.trueMagic.enviroment.GridManager;
//import com.trueMagic.object.GameObject;
//import com.trueMagic.object.GameObjectManager;
//import com.trueMagic.utils.events.EventManager;
//import com.trueMagic.world.WorldManager;
//import java.util.ArrayList;
//
///**
// *
// * @author Declan Easton
// */
//public class PhysicsCore {
//    
//    private WorldManager world;
//    //private GridManager gridManager;
//    private GameObjectManager objectManager;
//    private EventManager eventManager;
//    private ArrayList<PhysicsBody> bodies;
//    
//    public Vector2 accelDueToGravity;
//    private final int minimumCollisionPointDistance = 100;
//    private final float collisionCheckLerpPercent = 0.05f;
//    private final float maxVelocity = 200;
//    
//    public PhysicsCore(WorldManager world) {
//        this.world = world;
//        //this.gridManager = world.getGridManager();
//        this.objectManager = world.getGameObjectManager();
//        this.bodies = new ArrayList<PhysicsBody>();
//        
//        eventManager = EventManager.getInstance();
//        
//        accelDueToGravity = new Vector2(0, (float) -9.8);
//        
//    }
//    
//    public void gameTick(float deltaTime) {
//        for(int i = 0; i < objectManager.getObjectCount(); i++) {
//            GameObject object = objectManager.getObject(i);
//            if(object != null) {
//                if(object.getBody().isMoves()) {
//                    objectMovement(deltaTime, object.getBody());
//                }
//            }
//        }
//    }
//    
//    public void addPhysicsBody(PhysicsBody body) {
//        bodies.add(body);
//        body.addForce(new Vector2(accelDueToGravity.x * body.getMass(), accelDueToGravity.y * body.getMass()));
//    }
//    
//    public PhysicsBody getPhyicsBody(int i) {
//        return bodies.get(i);
//    }
//    
//    private void objectMovement(float deltaTime, PhysicsBody body) {
//        
//        if(!body.isMoves()) {
//            return;
//        }
//        
//        Vector2 totalForce = new Vector2();
//        for(Vector2 force : body.getForces()) {
//            if(force != null) {
//                totalForce.add(force);
//            }
//        }
//        Vector2 acceleration = new Vector2(new Vector2(totalForce.x / body.getMass(), totalForce.y / body.getMass()));
//        body.getVelocity().add(acceleration.x * deltaTime, acceleration.y * deltaTime);
//        body.setVelocity(new Vector2(Math.min(body.getVelocity().x, maxVelocity), Math.min(body.getVelocity().y, maxVelocity)));
//        
//        boolean collidesX = collisionCheck(body,
//                new Vector2(world.convertMetreToCoord(body.getVelocity().x * deltaTime) + body.getPosition().x, body.getPosition().y));
//        if(collidesX) {
//            body.getVelocity().x /= 2;
//        } else {
//            body.setPosition(body.getPosition().add(world.convertMetreToCoord(body.getVelocity().x * deltaTime), 0));
//        }
//
//        boolean collidesY = collisionCheck(body,
//                new Vector2(body.getPosition().x, world.convertMetreToCoord(body.getVelocity().y * deltaTime) + body.getPosition().y));
//        if(collidesY) {
//            body.getVelocity().y /= 2;
//        } else {
//            body.setPosition(body.getPosition().add(0, world.convertMetreToCoord(body.getVelocity().y * deltaTime)));
//        }
//        updateCheckCollisionPoints(body);
//    }
//    
//    private void heatConductance(float deltaTime) {
//    
//    }
//    
//    private void updateCheckCollisionPoints(PhysicsBody movingBody) {
//        for(CollisionCheckPoint point : movingBody.getCollisionCheckPoints()) {
//            if(point != null) {
//                point.setColliding(false);
//                for(PhysicsBody otherBody : bodies) {
//                    if(movingBody != otherBody && otherBody.isCollides()) {
//                        if(otherBody.contains(point.getPosition())) {
//                            point.setColliding(true);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }
//    
//    private boolean collisionCheck(PhysicsBody movingBody, Vector2 newPosition) {
//        if(!movingBody.isCollides()) {
//            return false;
//        }
//        boolean collides = false;
//        Vector2 originalPoint = new Vector2(movingBody.getPosition().x, movingBody.getPosition().y);
//        movingBody.setPosition(newPosition);
//        for(PhysicsBody otherBody : bodies) {
//            if(movingBody != otherBody && otherBody.isCollides() && movingBody.collides(otherBody)) {
//                collides = true;
//            }
//        }
//        movingBody.setPosition(originalPoint);
//        return collides;
//    }
//    
////    private Vector2[] generateCollisionPoints(GameObject object) {
////        int numberOfVerticalCollisionPoints = 2;
////        float collisionPointVerticalDistance = object.getHeight() / (numberOfVerticalCollisionPoints - 1);
////        while(collisionPointVerticalDistance > minimumCollisionPointDistance) {
////            numberOfVerticalCollisionPoints++;
////            collisionPointVerticalDistance = object.getHeight() / (numberOfVerticalCollisionPoints - 1);
////        }
////
////        int numberOfHorizontalCollisionPoints = 2;
////        float collisionPointHorizontalDistance = object.getWidth() / (numberOfHorizontalCollisionPoints - 1);
////        while(collisionPointHorizontalDistance > minimumCollisionPointDistance) {
////            numberOfHorizontalCollisionPoints++;
////            collisionPointHorizontalDistance = object.getWidth() / (numberOfHorizontalCollisionPoints - 1);
////        }
////
////        Vector2[] newCollisionPoints = new Vector2[numberOfVerticalCollisionPoints * 2 + (numberOfHorizontalCollisionPoints * 2 - 4)];
////        
////        int index = 0;
////        
////        int i = 0;
////        while(i < numberOfVerticalCollisionPoints) {
////            newCollisionPoints[index] = new Vector2(0, collisionPointVerticalDistance * i);
////            index++;
////            newCollisionPoints[index] = new Vector2(object.getWidth(), collisionPointVerticalDistance * i);
////            index++;
////            i++;
////        }
////        
////        i = 1;
////        while(i < numberOfHorizontalCollisionPoints - 1) {
////            newCollisionPoints[index] = new Vector2(collisionPointHorizontalDistance * i, 0);
////            index++;
////            newCollisionPoints[index] = new Vector2(collisionPointHorizontalDistance * i, object.getHeight());
////            index++;
////        }
////        
////        return newCollisionPoints;
////    }
//}
