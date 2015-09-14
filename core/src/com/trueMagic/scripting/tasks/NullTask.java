/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.scripting.tasks;

/**
 *
 * @author Declan Easton
 */
public class NullTask extends Task {

    @Override
    public boolean gameTick(float deltaTime) {
        return false;
    }
    
}
