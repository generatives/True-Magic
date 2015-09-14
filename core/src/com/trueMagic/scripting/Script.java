/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.scripting;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 *
 * @author Declan Easton
 */
public class Script {
    
    private String path;
    private CompiledScript compiledScript;
    private Bindings bindings;
    private String plainScript;
    private String name;
    
    public Script(String name, Bindings bindings) {
        
    }
    
    public Script(String name, String plainScript, Bindings bindings, ScriptEngine engine) throws ScriptException {
        this.path = path;
        this.name = name;
        this.plainScript = plainScript;
        this.bindings = bindings;
        
        compiledScript = ((Compilable)engine).compile(plainScript);
    }
    
    public void bindNewObject(String name, Object object) {
        bindings.put(name, object);
    }
    
    public CompiledScript getCompiledScript() {
        return this.compiledScript;
    }
    
    public Bindings getBindings() {
        return this.bindings;
    }
}
