package com.trueMagic.scriptingMKII;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.trueMagic.game.screens.MainGame;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import com.trueMagic.scriptingMKII.api.PlayerControlApi;

/**
 * Created by Declan Easton on 2015-08-15.
 */
public class ScriptLoader {

    private HashMap<String, ExecutableScript> scripts;
    private Context context;
    private ScriptableObject scope;

    public ScriptLoader(MainGame game) {
        this.context = Context.enter();
        this.context.setOptimizationLevel(9);
        this.scope = context.initSafeStandardObjects();

        this.scripts = new HashMap<String, ExecutableScript>();
        loadSavedScripts();

        PlayerControlApi.setPlayer(game.world.getPlayer());

        try {
            ScriptableObject.defineClass(scope, PlayerControlApi.PlayerControlHostObject.class);
            Scriptable player = context.newObject(scope, "PlayerAPI");
            scope.put("Player", scope, player);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    private void newScript(String name, String script) {
        scripts.put(name, new ExecutableScript(context.compileString(script, name, 0, null), context, scope));
    }

    private void loadSavedScripts() {
        FileHandle scriptFolder = Gdx.files.external("/Scripts");
        FileHandle[] scriptFiles = scriptFolder.list();
        for(FileHandle scriptFile: scriptFiles) {
            this.newScript(scriptFile.nameWithoutExtension(), scriptFile.readString());
        }
    }

    public ExecutableScript getScript(String name) {
        return scripts.get(name);
    }
}
