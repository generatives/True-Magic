///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.trueMagic.scripting.scriptedBehavior;
//
//import com.trueMagic.game.PlayerInput;
//import com.trueMagic.scripting.Script;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.script.ScriptException;
//
///**
// *
// * @author Declan Easton
// */
//public class InputOverrideBehavior extends Behavior {
//    private final HashMap<Integer, Behavior> keyDown;
//    private final HashMap<Integer, Behavior> keyUp;
//    private final HashMap<Integer, Behavior> keyTyped;
//    
//    private final HashMap<Integer, Behavior> touchDown;
//    private final HashMap<Integer, Behavior> touchUp;
//    private final HashMap<Integer, Behavior> touchDragged;
//    
//    private final HashMap<Integer, Behavior> mouseMoved;
//    private final HashMap<Integer, Behavior> scrolled;
//    
//    private final HashMap<Integer, Behavior> oldKeyDown;
//    private final HashMap<Integer, Behavior> oldKeyUp;
//    private final HashMap<Integer, Behavior> oldKeyTyped;
//    
//    private final HashMap<Integer, Behavior> oldTouchDown;
//    private final HashMap<Integer, Behavior> oldTouchUp;
//    private final HashMap<Integer, Behavior> oldTouchDragged;
//    
//    private final HashMap<Integer, Behavior> oldMouseMoved;
//    private final HashMap<Integer, Behavior> oldScrolled;
//    
//    private final PlayerInput playerInput;
//    
//    public InputOverrideBehavior(PlayerInput input) {
//        keyDown = new HashMap<Integer, Behavior>();
//        keyUp = new HashMap<Integer, Behavior>();
//        keyTyped = new HashMap<Integer, Behavior>();
//        
//        touchDown = new HashMap<Integer, Behavior>();
//        touchUp = new HashMap<Integer, Behavior>();
//        touchDragged = new HashMap<Integer, Behavior>();
//        
//        mouseMoved = new HashMap<Integer, Behavior>();
//        scrolled = new HashMap<Integer, Behavior>();
//        
//        oldKeyDown = new HashMap<Integer, Behavior>();
//        oldKeyUp = new HashMap<Integer, Behavior>();
//        oldKeyTyped = new HashMap<Integer, Behavior>();
//        
//        oldTouchDown = new HashMap<Integer, Behavior>();
//        oldTouchUp = new HashMap<Integer, Behavior>();
//        oldTouchDragged = new HashMap<Integer, Behavior>();
//        
//        oldMouseMoved = new HashMap<Integer, Behavior>();
//        oldScrolled = new HashMap<Integer, Behavior>();
//        
//        this.playerInput = input;
//    }
//    
//    public void bindKeyDownScript(int i, Behavior script) {
//        keyDown.put(i, script);
//    }
//    
//    public void bindKeyUpScript(int i, Behavior script) {
//        keyUp.put(i, script);
//    }
//    
//    public void bindTouchDownScript(int i, Behavior script) {
//        touchDown.put(i, script);
//    }
//
//    @Override
//    public void Activate() {
//        Iterator numIter = keyDown.keySet().iterator();
//        while(numIter.hasNext()) {
//            int i = (Integer)(numIter.next());
//            oldKeyDown.put(i, playerInput.bindKeyDownScript(i, keyDown.get(i)));
//        }
//        
//        numIter = keyUp.keySet().iterator();
//        while(numIter.hasNext()) {
//            int i = (Integer)(numIter.next());
//            oldKeyUp.put(i, playerInput.bindKeyUpScript(i, keyUp.get(i)));
//        }
//        
//        numIter = touchDown.keySet().iterator();
//        while(numIter.hasNext()) {
//            int i = (Integer)(numIter.next());
//            oldTouchDown.put(i, playerInput.bindTouchDownBehavior(i, touchDown.get(i)));
//        }
//        
//        try {
//            super.Activate();
//        } catch (ScriptException ex) {
//            Logger.getLogger(InputOverrideBehavior.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public void Deactivate() {
//        Iterator numIter = oldKeyDown.keySet().iterator();
//        while(numIter.hasNext()) {
//            int i = (Integer)(numIter.next());
//            keyDown.put(i, playerInput.bindKeyDownBehavior(i, oldKeyDown.get(i)));
//        }
//        
//        numIter = oldKeyUp.keySet().iterator();
//        while(numIter.hasNext()) {
//            int i = (Integer)(numIter.next());
//            keyUp.put(i, playerInput.bindKeyUpBehavior(i, oldKeyUp.get(i)));
//        }
//        
//        numIter = oldTouchDown.keySet().iterator();
//        while(numIter.hasNext()) {
//            int i = (Integer)(numIter.next());
//            oldTouchDown.put(i, playerInput.bindTouchDownBehavior(i, oldTouchDown.get(i)));
//        }
//        
//        try {
//            super.Deactivate();
//        } catch (ScriptException ex) {
//            Logger.getLogger(InputOverrideBehavior.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    
//}
