package com.trueMagic.game;

import com.badlogic.gdx.Game;
import com.trueMagic.game.screens.MainGame;
import com.trueMagic.game.screens.MainMenu;
import com.trueMagic.maps.SectionLoader;

public class TrueMagic extends Game {
    
    private MainMenu menu;

    @Override
    public void create() {
        SectionLoader.load();
        
        menu = new MainMenu(this);
        setScreen(menu);
    }
    
    public void loadGame(PlayerProfile profile) {
        setScreen(new MainGame(profile));
    }
	
    public void goToMainMenu() {
        setScreen(menu);
    }
}
