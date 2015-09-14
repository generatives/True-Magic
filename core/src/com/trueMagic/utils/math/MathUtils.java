/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.utils.math;

/**
 *
 * @author Declan Easton
 */
public class MathUtils {
    
    public static boolean between(float i0, float i1, float i2) {
        return (i0 >= i1 && i0 <= i2) || (i0 >= i2 && i0 <= i1);
    }
}
