package com.hy.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChinamutualfundstockportfolioDto implements Serializable {
    private String wind;

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
