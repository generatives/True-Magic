/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.scripting;

import com.trueMagic.object.GameObject;
import com.trueMagic.scripting.api.apiObjects.ISpellCaster;
import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptException;

/**
 *
 * @author Declan Easton
 */
public class ExecutableScript {
    
    private CompiledScript script;
    private Bindings bindings;
    
    public ExecutableScript(CompiledScript script, Bindings bindings) {
        this.script = script;
        this.bindings = bindings;
    }
    
    public void execute() throws ScriptException {
        script.eval(bindings);
    }
    
    public void setSpellcaster(ISpellCaster object) {
        this.bindings.put("Spellcaster", object);
    }
    
    public void setGameObject(GameObject object) {
        this.bindings.put("Body", object);
    }
    
}
