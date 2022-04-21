package com.gtchenr.pojo.use;

public enum Operate {
    LIKE("like"), COLLECT("collect"), UPDATE("update"), ADD("add"), DELETE("delete"), LOOK("look"), COMMENT("comment");

    private String name;

    Operate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
