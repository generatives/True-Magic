/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.utils.math;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Declan Easton
 */
public class PolygonUtils {
    
    public static <T extends Point> List<T> simplify(List<T> polygon) {
        List<T> points = new ArrayList<T>();
        T lastPoint = polygon.get(0);
        T point;
        Direction lastDir = Direction.NONE;
        Direction thisDir;
        
        for(int i = 1; i < polygon.size(); i++) {
            point = polygon.get(i);
            float diffX = point.x - lastPoint.x;
            float diffY = point.y - lastPoint.y;
            
            if(diffX == 0 && diffY < 0) {
                thisDir = Direction.DOWN;
            } else if(diffX == 0 && diffY > 0) {
                thisDir = Direction.UP;
            } else if(diffX < 0 && diffY == 0) {
                thisDir = Direction.LEFT;
            } else if(diffX > 0 && diffY == 0) {
                thisDir = Direction.RIGHT;
            } else {
                thisDir = Direction.NONE;
            }
            
            if(lastDir != thisDir) {
                points.add(lastPoint);
            }
            
            lastPoint = point;
            lastDir = thisDir;
        }
        
        return points;
    }
}
