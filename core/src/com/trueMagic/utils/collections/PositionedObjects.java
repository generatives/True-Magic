/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.utils.collections;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Declan Easton
 */
public class PositionedObjects<T> {
    
    private float bucketSize;
    private ArrayList<T>[][] objects;
    
    public PositionedObjects(float top, float bottom, float right, float left) {
        float width = 0;
        float height = 0;
        bucketSize = (left - right) / 1000;
        objects = new ArrayList[(int)(width / bucketSize)][(int)(height / bucketSize)];
    }
    
    public void add(T object, float x, float y) {
        objects[(int)(x / bucketSize)][(int)(y / bucketSize)].add(object);
    }
    
    private class PositionedNode<T> {
        
        T object;
        float x, y;
        boolean isLeaf;
        
        public PositionedNode(T object, float x, float y) {
            this.object = object;
            this.x = x;
            this.y = y;
        }
    }
}
