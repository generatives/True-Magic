/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.scripting.api;

import com.trueMagic.game.TrueMagic;
import com.trueMagic.game.screens.MainGame;
import com.trueMagic.scripting.APIObjectProvider;
import com.trueMagic.scripting.api.apiObjects.MouseTarget;

/**
 *
 * @author Declan Easton
 */
public class ControlApi implements APIObjectProvider {
    
    private MainGame game;
    public MouseTarget Mouse;
    
    public ControlApi(MainGame game) {
        this.game = game;
        this.Mouse = new MouseTarget();
    }

    @Override
    public Object getAPIObject() {
        return this;
    }
    
    
}
