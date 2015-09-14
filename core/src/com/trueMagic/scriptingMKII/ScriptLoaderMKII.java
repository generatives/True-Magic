package com.trueMagic.scriptingMKII;

import com.artemis.Manager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.trueMagic.game.screens.MainGame;
import com.trueMagic.scriptingMKII.api.PlayerControlApi;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by Declan Easton on 2015-08-15.
 */
public class ScriptLoaderMKII extends Manager {
    private Context context;
    private ArrayList<Script> scripts;

    public ScriptLoaderMKII(MainGame game) {
        this.context = Context.enter();
        this.context.setOptimizationLevel(9);
        this.playerScope = context.initSafeStandardObjects();

        loadSavedScripts();


    }

    private void loadSavedScripts() {
        FileHandle scriptFolder = Gdx.files.external("/Scripts");
        FileHandle[] scriptFiles = scriptFolder.list();
        for(FileHandle scriptFile: scriptFiles) {
            scripts.add(context.compileString(scriptFile.readString(), scriptFile.nameWithoutExtension(), 0, null));
            script.exec(context, playerScope);
        }
    }

    public ScriptableObject getPlayerScope() {
        ScriptableObject
        PlayerControlApi.setPlayer(game.world.getPlayer());
        try {
            ScriptableObject.defineClass(playerScope, PlayerControlApi.PlayerControlHostObject.class);
            Scriptable player = context.newObject(playerScope, "PlayerAPI");
            playerScope.put("Player", playerScope, player);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
