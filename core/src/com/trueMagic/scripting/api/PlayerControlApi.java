/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.scripting.api;

import com.trueMagic.game.TrueMagic;
import com.trueMagic.object.creature.Player;
import com.trueMagic.game.screens.MainGame;
import com.trueMagic.scripting.APIObjectProvider;
import com.trueMagic.scripting.tasks.Task;
import com.trueMagic.scripting.tasks.Tasks;

/**
 *
 * @author Declan Easton
 */

public class PlayerControlApi implements APIObjectProvider {
    
    Player player;
    
    public PlayerControlApi(MainGame game) {
        player = game.world.getPlayer();
    }
    
    private class MoveLeft extends Task {
        @Override
        public boolean gameTick(float deltaTime) {
            player.moveLeft();
            return true;
        }
    }
    
    public void moveLeft() {
        Tasks.addTask(new MoveLeft());
    }
    
    private class MoveRight extends Task {
        @Override
        public boolean gameTick(float deltaTime) {
            player.moveRight();
            return true;
        }
    }
    
    public void moveRight() {
        Tasks.addTask(new MoveRight());
    }
    
    private class MoveDown extends Task {
        @Override
        public boolean gameTick(float deltaTime) {
            player.moveDown();
            return true;
        }
    }
    
    public void moveDown() {
        Tasks.addTask(new MoveDown());
    }
    
    private class StopMove extends Task {
        @Override
        public boolean gameTick(float deltaTime) {
            player.stopMove();
            return true;
        }
    }
    
    public void stopMove() {
        Tasks.addTask(new StopMove());
    }
    
    private class Jump extends Task {
        @Override
        public boolean gameTick(float deltaTime) {
            player.jump();
            return true;
        }
    }
    
    public void jump() {
        Tasks.addTask(new Jump());
    }

    @Override
    public Object getAPIObject() {
        return this;
    }
    
}
