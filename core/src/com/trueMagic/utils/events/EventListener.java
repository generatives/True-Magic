/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.utils.events;

import com.trueMagic.utils.events.arbitraryEvent.ArbitraryEvent;

/**
 *
 * @author Declan Easton
 */
public interface EventListener {
    public boolean event(ArbitraryEvent event);
    public boolean event(int eventName, int details);
}
