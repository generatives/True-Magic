/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.scripting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.trueMagic.game.TrueMagic;
import com.trueMagic.game.screens.MainGame;
import com.trueMagic.scripting.api.ControlApi;
import com.trueMagic.scripting.api.PlayerControlApi;
import com.trueMagic.scripting.api.TelekineticsApi;
import com.trueMagic.utils.filesystem.FileSystemUtils;
import java.util.HashMap;
import java.util.Iterator;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Declan Easton
 */
public class ScriptLoader {
    
    private MainGame game;
    
    private ScriptEngineManager engineManager;
    private HashMap<String, ScriptEngine> namedScriptEngines;
    private ScriptEngine scriptEngine;
    private Bindings baseBindings;
    
    private HashMap<String, Script> scripts;
    
    private final String sandboxInitialScript = "-- save a pointer to globals that would be unreachable in sandbox\n" +
    "local e=_ENV\n" +
    "\n" +
    "-- sample sandbox environment\n" +
    "sandbox_env = {\n" +
    "  ipairs = ipairs,\n" +
    "  next = next,\n" +
    "  pairs = pairs,\n" +
    "  pcall = pcall,\n" +
    "  tonumber = tonumber,\n" +
    "  tostring = tostring,\n" +
    "  type = type,\n" +
    "  unpack = unpack,\n" +
    "  coroutine = { create = coroutine.create, resume = coroutine.resume, \n" +
    "      running = coroutine.running, status = coroutine.status, \n" +
    "      wrap = coroutine.wrap },\n" +
    "  string = { byte = string.byte, char = string.char, find = string.find, \n" +
    "      format = string.format, gmatch = string.gmatch, gsub = string.gsub, \n" +
    "      len = string.len, lower = string.lower, match = string.match, \n" +
    "      rep = string.rep, reverse = string.reverse, sub = string.sub, \n" +
    "      upper = string.upper },\n" +
    "  table = { insert = table.insert, maxn = table.maxn, remove = table.remove, \n" +
    "      sort = table.sort },\n" +
    "  math = { abs = math.abs, acos = math.acos, asin = math.asin, \n" +
    "      atan = math.atan, atan2 = math.atan2, ceil = math.ceil, cos = math.cos, \n" +
    "      cosh = math.cosh, deg = math.deg, exp = math.exp, floor = math.floor, \n" +
    "      fmod = math.fmod, frexp = math.frexp, huge = math.huge, \n" +
    "      ldexp = math.ldexp, log = math.log, log10 = math.log10, max = math.max, \n" +
    "      min = math.min, modf = math.modf, pi = math.pi, pow = math.pow, \n" +
    "      rad = math.rad, random = math.random, sin = math.sin, sinh = math.sinh, \n" +
    "      sqrt = math.sqrt, tan = math.tan, tanh = math.tanh },\n" +
    "  os = { clock = os.clock, difftime = os.difftime, time = os.time },\n" + 
    "function run_sandbox(sb_env, sb_func, ...)\n" +
    "  local sb_orig_env=_ENV\n" +
    "  if (not sb_func) then return nil end\n" +
    "  _ENV=sb_env\n" +
    "  local sb_ret={e.pcall(sb_func, ...)}\n" +
    "  _ENV=sb_orig_env\n" +
    "  return e.table.unpack(sb_ret)\n" +
    "end";
    
    public ScriptLoader(MainGame game) {
        engineManager = new ScriptEngineManager();
        this.game = game;
        
	    namedScriptEngines = new HashMap<String, ScriptEngine>();
        scriptEngine = engineManager.getEngineByName("luaj");
        //scriptEngine.eval(sandboxInitialScript);
        
        baseBindings = scriptEngine.createBindings();
        
        scripts = new HashMap<String, Script>();
        try {
            loadSavedScripts();
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        bindNewObject("Player", new PlayerControlApi(game));
        bindNewObject("Control", new ControlApi(game));
        //bindNewObject("Projectiles", new ProjectilesApi(game));
        //bindNewObject("Input", new InputApi(game));
        bindNewObject("Telekinetics", new TelekineticsApi());
    }
    
    private void loadSavedScripts() throws ScriptException {
        FileHandle scriptFolder = Gdx.files.external("/Scripts");
        FileHandle[] scriptFiles = scriptFolder.list();
        for(FileHandle scriptFile: scriptFiles) {
           this.newScript(scriptFile.nameWithoutExtension(), scriptFile.readString());
        }
    }
    
    private ScriptEngine initializeNewNamedEngine(String name) throws ScriptException {
        ScriptEngine engine = engineManager.getEngineByName("luaj");
        //engine.eval(sandboxInitialScript);
        namedScriptEngines.put(name, engine);
        return engine;
    }
    
    private void safeFunctionCall() {
        
    }
    
    private void loadScript(String name, String path) {
        
    }
    
    private void newScript(String name, String script) throws ScriptException {
        Bindings bindings = scriptEngine.createBindings();
        
        Iterator objectIter = baseBindings.values().iterator();
        Iterator nameIter = baseBindings.keySet().iterator();
        while(objectIter.hasNext() && nameIter.hasNext()) {
            bindings.put((String) nameIter.next(), objectIter.next());
        }
        scripts.put(name, new Script(name, script, bindings, scriptEngine));
    }
    
    public void bindNewObject(String name, Object object) {
        baseBindings.put(name, object);
        Iterator iter = scripts.values().iterator();
        while(iter.hasNext()) {
            ((Script) iter.next()).bindNewObject(name, object);
        }
    }
    
    public ExecutableScript getScript(String name) {
        Script template = scripts.get(name);
        return new ExecutableScript(template.getCompiledScript(), template.getBindings());
    }
}
