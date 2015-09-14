///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.trueMagic.scripting.scriptedBehavior;
//
//import com.trueMagic.scripting.Script;
//import javax.script.ScriptException;
//
///**
// *
// * @author Declan Easton
// */
//public abstract class Behavior {
//    private Script activate, deactivate;
//    private Script gameTickScript;
//    
//    public void Activate() throws ScriptException {
//        if(activate != null) {
//            activate.execute(); 
//        }
//    }
//    
//    public void Deactivate() throws ScriptException {
//        if(deactivate != null) {
//            deactivate.execute();
//        }
//    }
//    
//    public void gameTick() throws ScriptException {
//        if(gameTickScript != null) {
//            gameTickScript.execute();
//        }
//    }
//    
//    public void bindActivateScript(Script script) {
//        this.activate = script;
//    }
//    
//    public void bindDeactivateScript(Script script) {
//        this.deactivate = script;
//    }
//    
//    public void bindGameTickScript(Script script) {
//        this.gameTickScript = script;
//    }
//}
