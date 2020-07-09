package com.example.demo2.dto;

import java.util.List;

/**
 * 曲线返回公共实体
 * */
public class SeriesBo {

    // 基金ID
    private String indCode;

    // 基金名称
    private String indName;

    // 公共出参
    private List<List> data;

    public String getIndCode() {
        return indCode;
    }

    public void setIndCode(String indCode) {
        this.indCode = indCode;
    }

    public String getIndName() {
        return indName;
    }

    public void setIndName(String indName) {
        this.indName = indName;
    }

    public List<List> getData() {
        return data;
    }

    public void setData(List<List> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SeriesBo{" +
                "indCode='" + indCode + '\'' +
                ", indName='" + indName + '\'' +
                ", data=" + data +
                '}';
    }
}
