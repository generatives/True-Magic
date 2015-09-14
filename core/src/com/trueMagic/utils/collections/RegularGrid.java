/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.utils.collections;

import java.util.ArrayList;

/**
 *
 * @author Declan Easton
 * @param <T>
 */
public class RegularGrid<T> {
    
    private final float spacing;
    private final DynamicArray<DynamicArray<T>> grid;
    
    public RegularGrid(float spacing) {
        this.spacing = spacing;
        grid = new DynamicArray<DynamicArray<T>>();
    }
    
    public void setTruncated(T object, float x, float y) {
        if(x >= 0 && y >= 0) {
            grid.get((int)(x / spacing)).set((int)(y / spacing), object);
        }
    }
    
    public Object getTruncated(float x, float y) {
        if(x >= 0 && y >= 0 && x < realXBound() && y < realYBound()) {
            return grid.get((int)(x / spacing)).get((int)(y / spacing));
        }
        return null;
    }
    
    public T get(float x, float y) {
        x /= spacing;
        y /= spacing;
        DynamicArray<T> yList = grid.get(Math.round(x));
        if(yList == null) {
            yList = new DynamicArray<T>();
            grid.set(Math.round(x), yList);
        }
        return yList.get(Math.round(y));
    }
    
    public void set(T object, float x, float y) {
        x /= spacing;
        int xIndex = Math.round(x);
        y /= spacing;
        int yIndex = Math.round(y);
        DynamicArray<T> yList = grid.get(xIndex);
        if(yList == null) {
            yList = new DynamicArray<T>();
            grid.set(xIndex, yList);
        }
        yList.set(yIndex, object);
    }
    
    public T getIndex(int x, int y) {
        DynamicArray<T> yList = grid.get(x);
        if(yList == null) {
            yList = new DynamicArray<T>();
            grid.set(x, yList);
        }
        return yList.get(y);
    }
    
    public void setIndex(int xIndex, int yIndex, T object) {
        DynamicArray<T> yList = grid.get(xIndex);
        if(yList == null) {
            yList = new DynamicArray<T>();
            grid.set(xIndex, yList);
        }
        yList.set(yIndex, object);
    }
    
    public float realXBound() {
        return grid.size() * spacing;
    }
    
    public float realYBound() {
        return grid.get(0).size() * spacing;
    }
    
    public int xBound() {
        return grid.size();
    }
    
    public int yBound() {
        int largestSize = 0;
        for(int i = 0; i < grid.size(); i++) {
            if(grid.get(i) != null && grid.get(i).size() > largestSize) {
                largestSize = grid.get(i).size();
            }
        }
        return largestSize;
    }
}
