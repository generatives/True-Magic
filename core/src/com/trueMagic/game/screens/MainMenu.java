/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.trueMagic.game.PlayerProfile;
import com.trueMagic.game.TrueMagic;

/**
 *
 * @author Declan Easton
 */
public class MainMenu implements Screen {

    private Stage stage;
    private Table table;
    private TextButtonStyle buttonStyle;
    private TextButton loadGameButton, newGameButton, optionsButton, exitButton; 
    
    public MainMenu(final TrueMagic game) {
        stage = new Stage();
        
        buttonStyle = new TextButtonStyle();
        buttonStyle.font = new BitmapFont();
        
        loadGameButton = new TextButton("Load Game", buttonStyle);
        loadGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.loadGame(new PlayerProfile());
                
            }
        });
        
        newGameButton = new TextButton("New Game", buttonStyle);
        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.loadGame(new PlayerProfile());
            }
        });
        
        optionsButton = new TextButton("Options", buttonStyle);
        
        exitButton = new TextButton("Exit", buttonStyle);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                // or System.exit(0);
            }
        });
        
        table = new Table();
    }
    
    @Override
    public void show() {
        table.add(loadGameButton).size(150,60).padBottom(20).row();
        table.add(newGameButton).size(150,60).padBottom(20).row();
        table.add(optionsButton).size(150,60).padBottom(20).row();
        table.add(exitButton).size(150,60).padBottom(20).row();
        
        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        
        
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
        stage.dispose();
    }
    
}
