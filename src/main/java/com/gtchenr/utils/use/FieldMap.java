package com.gtchenr.utils.use;

import java.util.HashMap;
import java.util.Map;


public class FieldMap {

    public static final Map<String, FieldCheck> reportFieldMap = new HashMap<>();

    /**
     * 初始化reportFieldMap，添加要放入document的属性名称和Field的类型
     */
    static {
        reportFieldMap.put("", FieldCheck.STORE_FIELD);

    }
}
