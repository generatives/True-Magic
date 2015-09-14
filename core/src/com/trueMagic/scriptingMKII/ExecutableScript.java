package com.trueMagic.scriptingMKII;


import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;

/**
 * Created by Declan Easton on 2015-08-15.
 */
public class ExecutableScript {

    private Script script;
    private Context context;
    private Scriptable scope;

    public ExecutableScript(Script script, Context context, Scriptable scope) {
        this.script = script;
        this.context = context;
        this.scope = scope;
    }

    public void execute() {
        script.exec(context, scope);
    }
}
