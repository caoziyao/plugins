package com.zel.commonutils;

import java.util.Calendar;
import java.util.Date;

public class ThreadUtils {
    /**
     * 获取当前行数
     * @return
     */
    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[1].getLineNumber();
    }

    public static void main(String[] args) {
    }
}
