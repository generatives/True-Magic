///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.trueMagic.physics;
//
//import com.badlogic.gdx.math.Vector2;
//import com.trueMagic.physics.collision.CollisionShape;
//
///**
// *
// * @author Declan Easton
// */
//public class PhysicsBody {
//    private Vector2 velocity, position;
//    private float mass;
//    private Vector2[] forces;
//    private float friction;
//    private boolean collides, moves;
//    private CollisionShape[] shapes;
//    private CollisionCheckPoint[] collisionCheckPoints;
//    
//    public PhysicsBody(PhysicsCore physicsCore, Vector2 position, Vector2 velocity, float mass, float friction,
//            boolean collides, boolean moves, CollisionShape[] shapes, CollisionCheckPoint[] collisionCheckPoints) {
//        
//        this.position = position;
//        this.velocity = velocity;
//        this.mass = mass;
//        this.friction = friction;
//        this.collides = collides;
//        this.moves = moves;
//        this.shapes = shapes;
//        this.collisionCheckPoints = collisionCheckPoints;
//        
//        this.forces = new Vector2[10];
//        
//        physicsCore.addPhysicsBody(this);
//    }
//    
//    public PhysicsBody(PhysicsCore physicsCore, Vector2 position, float mass, float friction, CollisionShape[] shapes, CollisionCheckPoint[] collisionCheckPoints) {
//        this(physicsCore, position, new Vector2(), mass, friction, true, true, shapes, collisionCheckPoints);
//    }
// 
//    public CollisionCheckPoint[] getCollisionCheckPoints() {
//        return collisionCheckPoints;
//    }
//    
//    public Vector2[] getForces() {
//        return forces;
//    }
//    
//    public int addForce(Vector2 force) {
//        for(int i = 0; i < forces.length; i++) {
//            if(forces[i] == null) {
//                forces[i] = force;
//                return i;
//            }
//        }
//        int i = forces.length;
//        expandForceList(10);
//        forces[i] = force;
//        return i;
//    }
//    
//    public boolean removeForce(int i) {
//        if(i >= 0 && i < forces.length) {
//            forces[i] = null;
//            return true;
//        } else {
//            return false;
//        }
//    }
//    
//    public boolean removeForce(Vector2 force) {
//        for(int i = 0; i < forces.length; i++) {
//            if(forces[i] == force) {
//                forces[i] = null;
//                return true;
//            }
//        }
//        return false;
//    }
//    
//    public boolean expandForceList(int length) {
//            Vector2[] tempArray = new Vector2[forces.length + length];
//
//            for(int i = 0; i < tempArray.length; i++) {
//                if(i < forces.length) {
//                    tempArray[i] = forces[i];
//                } else {
//                    tempArray[i] = null;
//                }
//            }
//            setForces(tempArray);
//            return true;
//    }
//    
//    public boolean contains(Vector2 point) {
//        for(CollisionShape shape : getShapes()) {
//            if(shape.contains(point)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    
//    public boolean collides(PhysicsBody otherBody) {
//        for(CollisionShape shape : shapes) {
//            for(CollisionShape otherShape : otherBody.shapes) {
//                if(shape.collision(otherShape)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//    
//    public float getMass() {
//        return mass;
//    }
//    
//    public void setMass(float mass) {
//        this.mass = mass;
//    }
//    
//    public Vector2 getVelocity() {
//        return velocity;
//    }
//    
//    public void setVelocity(Vector2 velocity) {
//        this.velocity = velocity;
//    }
//    
//    public Vector2 getPosition() {
//        return position;
//    }
//    
//    public void setPosition(Vector2 position) {
//        this.position = position;
//        for(CollisionShape shape : shapes) {
//            shape.setCollisionPosition(position);
//        }
//        for(CollisionCheckPoint point : collisionCheckPoints) {
//            point.setPosition(position);
//        }
//    }
//
//    /**
//     * @param forces the forces to set
//     */
//    public void setForces(Vector2[] forces) {
//        this.forces = forces;
//    }
//
//    /**
//     * @return the friction
//     */
//    public float getFriction() {
//        return friction;
//    }
//
//    /**
//     * @param friction the friction to set
//     */
//    public void setFriction(float friction) {
//        this.friction = friction;
//    }
//
//    /**
//     * @return the collides
//     */
//    public boolean isCollides() {
//        return collides;
//    }
//
//    /**
//     * @param collides the collides to set
//     */
//    public void setCollides(boolean collides) {
//        this.collides = collides;
//    }
//
//    /**
//     * @return the moves
//     */
//    public boolean isMoves() {
//        return moves;
//    }
//
//    /**
//     * @param moves the moves to set
//     */
//    public void setMoves(boolean moves) {
//        this.moves = moves;
//    }
//
//    /**
//     * @return the shapes
//     */
//    public CollisionShape[] getShapes() {
//        return shapes;
//    }
//
//    /**
//     * @param shapes the shapes to set
//     */
//    public void setShapes(CollisionShape[] shapes) {
//        this.shapes = shapes;
//    }
//
//    /**
//     * @param collisionCheckPoints the collisionCheckPoints to set
//     */
//    public void setCollisionCheckPoints(CollisionCheckPoint[] collisionCheckPoints) {
//        this.collisionCheckPoints = collisionCheckPoints;
//    }
//}