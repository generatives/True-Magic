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
public abstract class Task {
    
    private Task next;
    
    public void setNext(Task task) {
        next = task;
    }
    
    public Task getNext() {
        return next;
    }
    
    public abstract boolean gameTick(float deltaTime);
}
