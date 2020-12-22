package com.zel.market.common;

import java.io.Serializable;

/**
 * env
 */
public final class Env implements Serializable {
    private static final ThreadLocal<AppContext> contextThreadLocal = new ThreadLocal();

    public Env() {
    }

    public static AppContext getContext() {
        return contextThreadLocal.get();
    }

    public static void setContext(AppContext context) {
        contextThreadLocal.set(context);
        if (context == null) {
            System.out.println("setcontent null");
        }
    }

    public static void remove(){
        contextThreadLocal.remove();
    }
}
