/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.scripting.api;

import com.trueMagic.object.GameObject;
import com.trueMagic.scripting.APIObjectProvider;
import com.trueMagic.scripting.api.apiObjects.ISpellCaster;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Declan Easton
 */
public class TelekineticsApi implements APIObjectProvider {
    
    @Override
    public Object getAPIObject() {
        return this;
    }
    
    public void applyForce(float x, float y, GameObject target, ISpellCaster source) {
        if(source.drawEnergy(0.015f * (float) Math.sqrt(x * x + y * y))) {
            target.getBody().applyForceToCenter(new Vec2(x, y));
        }
    }
}
