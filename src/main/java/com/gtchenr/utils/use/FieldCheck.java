package com.gtchenr.utils.use;

import java.util.Objects;

public enum FieldCheck {

    /**
     * 索引：false
     * 分词：true
     * 存储：true or false
     */
    STRING_FIELD_STORED, STRING_FIELD_UN_STORED,
    /**
     * 索引：true
     * 分词：true
     * 存储：false
     */
    FLOAT_POINT, DOUBLE_POINT, LONG_POINT, INT_POINT,
    /**
     * 索引：false
     * 分词：false
     * 存储：true
     */
    STORE_FIELD,
    /**
     * 是否索引：true
     * 是否分词：true
     * 是否存储：true or false
     */
    TEXT_FIELD_TRUE, TEXT_FIELD_FALSE, TEXT_FIELD_IO
}
