/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.object.enviroment;

import com.trueMagic.object.material.Material;
import com.trueMagic.utils.math.Point;

/**
 *
 * @author Declan Easton
 */
public class EnviromentPoint extends Point {
    
    private Material material;
    
    public EnviromentPoint(Material material, float x, float y) {
        this.material = material;
        this.x = x;
        this.y = y;
    }
    
    public static boolean materialEquals(EnviromentPoint point1, EnviromentPoint point2) {
        if(point1 != null && point2 != null) {
            return point1.material.properties.name.equalsIgnoreCase(point2.material.properties.name);
        }
        return false;
    }
    
    public boolean collides() {
        return this.material.properties.collides;
    }
}
