///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.trueMagic.scripting.api.apiObjects;
//
//import com.badlogic.gdx.math.Vector2;
//import com.trueMagic.game.TrueMagic;
//import com.trueMagic.object.GameObject;
//import com.trueMagic.object.spell.Projectile;
//import com.trueMagic.game.screens.MainGame;
//import com.trueMagic.scripting.ExecutableScript;
//import com.trueMagic.scripting.tasks.Task;
//import com.trueMagic.scripting.tasks.Tasks;
//
///**
// *
// * @author Declan Easton
// */
//public class ApiProjectile {
//
//    private ISpellCaster source;
//    private MainGame game;
//    private ExecutableScript behavior;
//
//    public ApiProjectile(ISpellCaster source, MainGame game) {
//        this.source = source;
//        this.game = game;
//    }
//
//    private class Launch extends Task {
//        private final ITarget target;
//        public Launch(ITarget target) {
//            this.target = target;
//        }
//        @Override
//        public boolean gameTick(float deltaTime) {
//            Vector2 position = source.getCastingPosition();
//            position.add(0, 1);
//            Vector2 mousePosition = game.unprojectVector(target.getPostion());
//            mousePosition.scl(1f / 64f);
//            Vector2 velocity = new Vector2();
//            velocity.set(mousePosition).sub(position).nor().scl(10);
//
//            Projectile projectile = new Projectile(game.world, position, velocity);
//            projectile.addBehavior(behavior);
//            return true;
//        }
//    }
//
//    public void launch(ITarget target) {
//        if(source.drawEnergy(10)) {
//            Tasks.addTask(new Launch(target));
//        }
//    }
//
//    public void enchant(String scriptName) {
//        behavior = game.scriptLoader.getScript(scriptName);
//    }
//
//}
