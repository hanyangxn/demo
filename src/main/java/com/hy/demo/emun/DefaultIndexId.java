package com.hy.demo.emun;


import java.util.Arrays;
import java.util.List;

/**
 * 默认指数查询
 */
public enum DefaultIndexId {

    // 改成指数收益需要查询的5个默认指数
    DEFAULT_INDEX_ID(Arrays.asList("000300.__", "000905.__", "000001.__", "399001.__", "399005.__", "399006.__")),
    // 改成沪深三百指数
    HS_300(Arrays.asList("000300.__")),
    ;
    private final List<String> indexIds;

    DefaultIndexId(List<String> indexIds) {
        this.indexIds = indexIds;

    }

    public List<String> getIndexIds() {
        return indexIds;
    }
}
