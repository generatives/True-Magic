/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.scripting.api.apiObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Declan Easton
 */
public class MouseTarget implements ITarget {

    @Override
    public Vector2 getPostion() {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        return new Vector2(x, y);
    }
    
}