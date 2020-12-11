package com.zel.market.common;

import java.io.Serializable;

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
        } else {
            System.out.println("set content " + context.toString());
        }
    }

    public static void remove(){
        contextThreadLocal.remove();
    }
}
