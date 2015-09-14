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
public class Margin {
    
    public int above, below;
    
    public Margin(int above, int below) {
        this.above = above;
        this.below = below;
    }
    
    public Margin(int margin) {
        this.above = margin / 2;
        this.below = margin / 2;
    }
    
    public boolean contains(int center, int toTest) {
        return toTest < center + above && toTest > center - below;
    }
}
