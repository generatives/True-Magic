package com.trueMagic.manager;

import com.artemis.Manager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.trueMagic.game.screens.MainGame;
import com.trueMagic.scriptingMKII.api.PlayerControlApi;
import com.trueMagic.system.PlayerControlSystem;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public class ScriptingManager extends Manager {
    private Context context;
    private ArrayList<Script> scripts;

    public ScriptingManager() {
        this.context = Context.enter();
        this.context.setOptimizationLevel(9);

        loadSavedScripts();
    }

    private void loadSavedScripts() {
        FileHandle scriptFolder = Gdx.files.external("/Scripts");
        FileHandle[] scriptFiles = scriptFolder.list();
        for(FileHandle scriptFile: scriptFiles) {
            scripts.add(context.compileString(scriptFile.readString(), scriptFile.nameWithoutExtension(), 0, null));
        }
    }

    public ScriptableObject getPlayerScope(PlayerControlSystem playerController) {
        ScriptableObject scope = context.initSafeStandardObjects();
        PlayerControlApi playerControlObject = new PlayerControlApi(playerController);
        scope.put("Player", scope, playerControlObject);
        for(Script script : scripts) {
            script.exec(context, scope);
        }
        return scope;
    }
}
