/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.utils.events;

import com.trueMagic.utils.events.arbitraryEvent.ArbitraryEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Declan Easton
 */
public class EventManager {
    
    private HashMap<String, ArrayList<EventListener>> events;
    private EventListener[][] internalEvents;
    
    private EventManager() {
        events = new HashMap<String, ArrayList<EventListener>>();
    }
    
    public ArrayList<EventListener> addEvent(String eventName) {
        return events.put(eventName, new ArrayList<EventListener>());
    }
    
    public boolean fireEvent(String eventName, String details, Class objectClass, Object object) {
        ArbitraryEvent event = new ArbitraryEvent(eventName, details, objectClass, object);
        
        if(events.containsKey(eventName)) {
            for(Iterator<EventListener> iter = events.get(eventName).listIterator();iter.hasNext();) {
                iter.next().event(event);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean subscribeToEvent(String eventName, EventListener listener) {
        if(events.containsKey(eventName)) {
            events.get(eventName).add(listener);
            return true;
        } else {
            ArrayList<EventListener> tempList = new ArrayList<EventListener>();
            tempList.add(listener);
            events.put(eventName, tempList);
            return false;
        }
    }
    
    public boolean fireInternalEvent(int eventName, int details) {
        if(eventName >= internalEvents.length || eventName < 0) {
            return false;
        } else {
            for(EventListener listenerSlot : internalEvents[eventName]) {
                if(listenerSlot != null) {
                    listenerSlot.event(eventName, details);
                }
            }
            return true;
        }
    }
    
    public boolean subscribeToInternalEvent(int eventName, EventListener listener) {
        if(eventName >= internalEvents.length || eventName < 0) {
            return false;
        } else {
            for(EventListener listenerSlot : internalEvents[eventName]) {
                if(listenerSlot == null) {
                    listenerSlot = listener;
                    return true;
                }
            }
            int i = internalEvents[eventName].length;
            extendInternalEventArray(eventName, 50);
            internalEvents[eventName][i] = listener;
            return true;
        }
    }
    
    public boolean unsubscribeToInternalEvent(int eventName, EventListener listener) {
        if(eventName >= internalEvents.length || eventName < 0) {
            return false;
        } else {
            for(EventListener listenerSlot : internalEvents[eventName]) {
                if(listenerSlot == listener) {
                    listenerSlot = null;
                    return true;
                }
            }
            return false;
        }
    }
    
    private boolean extendInternalEventArray(int eventName, int length) {
        
        if(eventName >= internalEvents.length || eventName < 0) {
            return false;
        } else {
            EventListener[] tempArray = new EventListener[internalEvents[eventName].length + length];

            for(int i = 0; i < tempArray.length; i++) {
                if(i < internalEvents[eventName].length) {
                    tempArray[i] = internalEvents[eventName][i];
                } else {
                    tempArray[i] = null;
                }
            }
            internalEvents[eventName] = tempArray;
            return true;
        }
    }
    
    public static EventManager getInstance() {
        return EventManagerHolder.INSTANCE;
    }
    
    private static class EventManagerHolder {

        private static final EventManager INSTANCE = new EventManager();
    }
}
