package com.gtchenr.utils.use;

import java.util.HashMap;
import java.util.Map;

public class FieldObjects<T> {

    private T t;
    private HashMap<String, FieldCheck> map;//表示要放入Document的方法名和FieldCheck
    private Class<T> tClass;


    public FieldObjects(T t, HashMap<String, FieldCheck> map, Class<T> tClass) {
        this.t = t;
        this.map = map;
        this.tClass = tClass;
    }

    public T getT() {
        return t;
    }

    public Map<String, FieldCheck> getMap() {
        return map;
    }

    public Class<T> gettClass() {
        return tClass;
    }
}
