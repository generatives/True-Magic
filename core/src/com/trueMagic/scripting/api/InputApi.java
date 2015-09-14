///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.trueMagic.scripting.api;
//
//import com.badlogic.gdx.Input;
//import com.trueMagic.game.PlayerInput;
//import com.trueMagic.game.TrueMagic;
//import com.trueMagic.game.screens.MainGame;
//import com.trueMagic.scripting.APIObjectProvider;
//import com.trueMagic.scripting.ExecutableScript;
//import com.trueMagic.scripting.Script;
//
///**
// *
// * @author Declan Easton
// */
//public class InputApi implements APIObjectProvider {
//
//    private final PlayerInput input;
//
//    public InputApi(MainGame game) {
//        input = game.playerInput;
//    }
//
//    @Override
//    public Object getAPIObject() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public int getKeyCode(String k) {
//        return Input.Keys.valueOf(k);
//    }
//
//    public int getButtonCode(String k) {
//        if(k.equals("LEFT")) {
//            return Input.Buttons.LEFT;
//        } else if(k.equals("RIGHT")) {
//            return Input.Buttons.RIGHT;
//        } else if(k.equals("MIDDLE")) {
//            return Input.Buttons.MIDDLE;
//        } else {
//            return -1;
//        }
//    }
//
//    public boolean bindKeyDown(int i, ExecutableScript script) {
//        return input.bindKeyDownScript(i, script);
//    }
//
//    public ExecutableScript getKeyDownScript(int i) {
//        return input.getKeyDownScript(i);
//    }
//
//    public boolean bindKeyUp(int i, ExecutableScript script) {
//        return input.bindKeyUpScript(i, script);
//    }
//
//    public ExecutableScript getKeyUpScript(int i) {
//        return input.getKeyUpScript(i);
//    }
//
//    public boolean bindTouchDown(int i, ExecutableScript script) {
//        return input.bindTouchDownScript(i, script);
//    }
//
//    public ExecutableScript getKeyTouchScript(int i) {
//        return input.getTouchDownScript(i);
//    }
//}
