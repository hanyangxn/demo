package com.example.demo2.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Chinamutualfundstockportfolio implements Serializable {
    private String sInfoWindcode;

    private String fPrtEnddate;

    private String crncyCode;

    private String sInfoStockwindcode;

    private BigDecimal fPrtStkvalue;

    private BigDecimal fPrtStkquantity;

    private static final Long serialVersionUID = 1L;
}