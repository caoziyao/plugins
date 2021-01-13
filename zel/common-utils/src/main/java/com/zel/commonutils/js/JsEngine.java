package com.zel.commonutils.js;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

//class Lodop {
//    public void ADD_PRINT_IMAGE(int top, int left, int width, int height, int path) throws Exception{
//        System.out.println("aaad");
//    }
//}

public class JsEngine {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine jsEngine = manager.getEngineByExtension("js");
        ScriptContext context = jsEngine.getContext();
        JSRUN JSRUN = new JSRUN();
        context.setAttribute("JSRUN", JSRUN, ScriptContext.GLOBAL_SCOPE);

        String jsFun = "JSRUN.ADD_PRINT_IMAGE(0, 0, 278.12213, 487.2131, 11);";
        try {
            jsEngine.eval(jsFun);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
