/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.utils.events.arbitraryEvent;

/**
 *
 * @author Declan Easton
 */
public class ArbitraryEvent {
    
    public String name, details;
    public Class objectClass;
    public Object object;
    
    public ArbitraryEvent(String name, String details, Class objectClass, Object object) {
        this.name = name;
        this.details = details;
        this.objectClass = objectClass;
        this.object = object;
    }
}
