package com.zel.market.annotation;

public enum ERoleType {
    NONE("None", "无身份"),
    MKTMANAGER("Admin", "Admin"),
    EXPRESSOR("Expressor", "后台管理人员");

    private final String code;
    private final String name;

    private ERoleType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String toCode() {
        return this.code;
    }

    public String toZhName() {
        return this.name;
    }
}
