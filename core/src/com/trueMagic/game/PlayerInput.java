/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.trueMagic.object.creature.Player;
import com.trueMagic.game.screens.MainGame;
import com.trueMagic.scripting.ExecutableScript;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;

/**
 *
 * @author Declan Easton
 */
public class PlayerInput implements InputProcessor {
    
    private ExecutableScript[] keyDown = new ExecutableScript[100];
    private ExecutableScript[] keyUp = new ExecutableScript[100];
    private ExecutableScript[] keyTyped = new ExecutableScript[100];
    
    private ExecutableScript[] touchDown = new ExecutableScript[10];
    private ExecutableScript[] touchUp = new ExecutableScript[10];
    private ExecutableScript[] touchDragged = new ExecutableScript[10];
    
    private ExecutableScript[] mouseMoved = new ExecutableScript[10];
    private ExecutableScript[] scrolled = new ExecutableScript[10];
    
    private Player player;
    
    public PlayerInput(MainGame game) {
        this.player = game.world.getPlayer();
    }
    
    public boolean bindKeyDownScript(int i, ExecutableScript script) {
        if(i >= 0 && i < keyDown.length) {
            script.setSpellcaster(player);
            keyDown[i] = script;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean bindKeyUpScript(int i, ExecutableScript script) {
        if(i >= 0 && i < keyUp.length) {
            script.setSpellcaster(player);
            keyUp[i] = script;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean bindTouchDownScript(int i, ExecutableScript script) {
        if(i >= 0 && i < touchDown.length) {
            script.setSpellcaster(player);
            touchDown[i] = script;
            return true;
        } else {
            return false;
        }
    }
    
    public ExecutableScript getKeyDownScript(int i) {
        if(i >= 0 && i < keyDown.length) {
            return keyDown[i];
        } else {
            return null;
        }
    }
    
    public ExecutableScript getKeyUpScript(int i) {
        if(i >= 0 && i < keyUp.length) {
            return keyUp[i];
        } else {
            return null;
        }
    }
    
    public ExecutableScript getTouchDownScript(int i) {
        if(i >= 0 && i < touchDown.length) {
            return touchDown[i];
        } else {
            return null;
        }
    }

    @Override
    public boolean keyDown(int i) {
        if(keyDown[i] != null) {
            try {
                long start = System.nanoTime();
                keyDown[i].execute();
                System.out.println("Execution took: " + (System.nanoTime() - start));
            } catch (ScriptException ex) {
                Logger.getLogger(PlayerInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean keyUp(int i) {
        if(keyUp[i] != null) {
            try {
                keyUp[i].execute();
            } catch (ScriptException ex) {
                Logger.getLogger(PlayerInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean keyTyped(char c) {
        if(keyTyped[0] != null) {
            try {
                keyTyped[0].execute();
            } catch (ScriptException ex) {
                Logger.getLogger(PlayerInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        if(touchDown[i3] != null) {
            try {
                touchDown[i3].execute();
            } catch (ScriptException ex) {
                Logger.getLogger(PlayerInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        if(touchUp[i3] != null) {
            try {
                touchUp[i3].execute();
            } catch (ScriptException ex) {
                Logger.getLogger(PlayerInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        if(touchDragged[0] != null) {
            try {
                touchDragged[0].execute();
            } catch (ScriptException ex) {
                Logger.getLogger(PlayerInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        if(mouseMoved[0] != null) {
            try {
                mouseMoved[0].execute();
            } catch (ScriptException ex) {
                Logger.getLogger(PlayerInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean scrolled(int i) {
        if(scrolled[0] != null) {
            try {
                scrolled[0].execute();
            } catch (ScriptException ex) {
                Logger.getLogger(PlayerInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }
    
}
