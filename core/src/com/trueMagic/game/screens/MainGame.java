/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.game.screens;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.trueMagic.entity.Entities;
import com.trueMagic.entity.xmlEntityDefinition.XmlEnitityBuilder;
import com.trueMagic.game.*;
import com.trueMagic.manager.ScriptingManager;
import com.trueMagic.maps.MapGenerator;
import com.trueMagic.scripting.tasks.Tasks;
import com.trueMagic.system.CameraFollowingSystem;
import com.trueMagic.system.PhysicsSystem;
import com.trueMagic.system.PlayerControlSystem;
import com.trueMagic.system.ShapeDrawingSystem;
import org.mozilla.javascript.ScriptableObject;

/**
 *
 * @author Declan Easton
 */
public class MainGame implements Screen {
    public final boolean debug = true;
    public World ceWorld;
    
    public MainGame(PlayerProfile profile) {
        WorldConfiguration config = new WorldConfiguration();

        PhysicsSystem physicsSystem = new PhysicsSystem();
        config.setSystem(physicsSystem);

        CameraFollowingSystem cameraSystem = new CameraFollowingSystem();
        config.setSystem(new ShapeDrawingSystem(cameraSystem));
        config.setSystem(cameraSystem);

        PlayerControlSystem playerController = new PlayerControlSystem();
        ScriptingManager scriptingManager = new ScriptingManager();
        ScriptableObject playerScope = scriptingManager.getPlayerScope(playerController);
        PlayerInputMKIII playerInput = new PlayerInputMKIII(playerScope);
        Gdx.input.setInputProcessor(playerInput);
        config.setSystem(playerController);
        config.setManager(scriptingManager);

        ceWorld = new World(config);

        XmlEnitityBuilder.processFiles(physicsSystem.physicalWorld, ceWorld, Entities.getInstance());
    }

    @Override
    public void show () {

        world.spawnPlayer(3, 9);
        MapGenerator.buildHome(world, new PlayerProfile());

    }

    @Override
    public void render (float deltaTime) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        deltaTime = Math.min(deltaTime, 0.1f);
        ceWorld.setDelta(deltaTime);
        ceWorld.process();
        Tasks.gameTick(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        
    }
}