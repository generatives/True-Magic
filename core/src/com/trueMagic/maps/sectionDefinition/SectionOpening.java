/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.maps.sectionDefinition;

import com.trueMagic.utils.math.Side;

/**
 *
 * @author Declan Easton
 */
public class SectionOpening {
    
    public float position, length;
    public Side side;
    
    public static boolean equals(SectionOpening sec1, SectionOpening sec2) {
        if(sec1.side == sec2.side) {
            float diff = Math.abs(sec2.position - sec1.position);
            if(diff <= 1) {
                diff = Math.abs(sec2.length - sec1.length);
                if(diff <= 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
