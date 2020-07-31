package com.example.demo2.dto;

import java.io.Serializable;

public class StageUpAndDownDto implements Serializable {

    private String fundCode;
    private String type;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
