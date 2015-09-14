package com.trueMagic.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptableObject;

/**
 * Created by Declan Easton on 2015-08-15.
 */
public class PlayerInputMKIII implements InputProcessor {

    private String[] keyDownNames = new String[110];
    private String[] keyUpNames = new String[110];

    private String[] touchDownNames = new String[3];
    private String[] touchUpNames = new String[3];

    ScriptableObject scope;
    Context context;
    Object[] args;

    public PlayerInputMKIII(ScriptableObject playerScope) {
        scope = playerScope;
        context = Context.getCurrentContext();
        args = new Object[0];


        for(int i = 1; i < keyDownNames.length; i++) {
            keyDownNames[i] = Input.Keys.toString(i).toUpperCase() + "_down";
        }

        for(int i = 1; i < keyUpNames.length; i++) {
            keyUpNames[i] = Input.Keys.toString(i).toUpperCase() + "_up";
        }

        touchDownNames[0] = "LEFT_MOUSE_down";
        touchUpNames[0] = "LEFT_MOUSE_up";
        touchDownNames[1] = "MIDDLE_MOUSE_down";
        touchUpNames[1] = "MIDDLE_MOUSE_up";
        touchDownNames[2] = "RIGHT_MOUSE_down";
        touchUpNames[2] = "RIGHT_MOUSE_up";
    }

    @Override
    public boolean keyDown(int i) {
        try {
            scope.callMethod(scope, keyDownNames[i], args);
        } catch(Exception e) {
            try {
                scope.callMethod(scope, "ANYKEY_down", args);
            } catch(Exception ex) {

            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        try {
            scope.callMethod(scope, keyUpNames[i], args);
        } catch(Exception e) {
            try {
                scope.callMethod(scope, "ANYKEY_up", args);
            } catch(Exception ex) {

            }
        }
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        try {
            scope.callMethod(scope, touchDownNames[i3], args);
        } catch(Exception e) {

        }
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        try {
            scope.callMethod(scope, touchUpNames[i3], args);
        } catch(Exception e) {

        }
        return true;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        try {
            scope.callMethod(scope, "TOUCH_DRAGGED", args);
        } catch(Exception e) {

        }
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        try {
            scope.callMethod(scope, "MOUSE_MOVED", args);
        } catch(Exception e) {

        }
        return true;
    }

    @Override
    public boolean scrolled(int i) {
        try {
            scope.callMethod(scope, "SCROLLED", args);
        } catch(Exception e) {

        }
        return true;
    }
}
