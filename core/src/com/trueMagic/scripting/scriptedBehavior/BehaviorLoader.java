///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.trueMagic.scripting.scriptedBehavior;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.utils.XmlReader;
//import com.badlogic.gdx.utils.XmlReader.Element;
//import com.badlogic.gdx.utils.XmlWriter;
//import com.trueMagic.game.TrueMagic;
//import com.trueMagic.utils.filesystem.FileSystemUtils;
//import java.io.IOException;
//import java.util.HashMap;
//
///**
// *
// * @author Declan Easton
// */
//public class BehaviorLoader {
//    
//    private static TrueMagic game;
//    private static XmlReader reader;
//    private static HashMap<String, Behavior> behaviors;
//    
//    public static void initialize(TrueMagic para_game) {
//        game = para_game;
//        reader = new XmlReader();
//    }
//    
//    public static void load() throws IOException {
//        FileHandle behaviorFolder = Gdx.files.external("/behavior");
//        FileHandle[] behaviorFiles = behaviorFolder.list();
//        for(FileHandle behaviorFile: behaviorFiles) {
//            Behavior behavior = parseBehaviorFromXML(reader.parse(behaviorFile));
//            String name = behaviorFile.nameWithoutExtension();
//            behaviors.put(name, behavior);
//            
//        }
//    }
//    
//    private static Behavior parseBehaviorFromXML(Element element) {
//        if(element.getName().equals("root")) {
//            Behavior behavior = null;
//            Element content = element.getChildByName("content");
//            if(content != null) {
//                String behaviorType = content.getAttribute("behaviorType");
//                if(behaviorType.equals("InputOverrideBehavior")) {
//                    behavior = parseInputOverrideBehavior(content, behavior);
//                } else if(behaviorType.equals("SingleScriptBehavior")) {
//                    behavior = parseSingleScriptBehavior(content, behavior);
//                }
//            }
//            return behavior;
//        } else {
//            return null;
//        }
//    }
//    
//    private static Behavior parseInputOverrideBehavior(Element element, Behavior behavior) {
//        behavior = new InputOverrideBehavior(game.playerInput);
//        parseActivateAndDeactivateScript(element, behavior);
//        return null;
//    }
//    
//    private static Behavior parseSingleScriptBehavior(Element element, Behavior behavior) {
//        Element activate = element.getChildByName("activate");
//        if(activate != null) {
//            String activateScriptName = activate.getText();
//            if(!activateScriptName.equals("")) {
//                return new SingleScriptBehavior(game.scriptLoader.getScript(activateScriptName));
//            }
//        }
//        return null;
//    }
//    
//    private static void parseActivateAndDeactivateScript(Element element, Behavior behavior) {
//        Element activate = element.getChildByName("activate");
//        if(activate != null) {
//            String activateScriptName = activate.getText();
//            if(!activateScriptName.equals("")) {
//                behavior.bindActivateScript(game.scriptLoader.getScript(activateScriptName));
//            }
//        }
//        Element deactivate = element.getChildByName("deactivate");
//        if(deactivate != null) {
//            String deactivateScriptName = deactivate.getText();
//            if(!deactivateScriptName.equals("")) {
//                behavior.bindActivateScript(game.scriptLoader.getScript(deactivateScriptName));
//            }
//        }
//    }
//    
//    public static void save() {
//        
//    }
//    
//    public static Behavior getBehavior(String name) {
//        return behaviors.get(name);
//    }
//    
//}
