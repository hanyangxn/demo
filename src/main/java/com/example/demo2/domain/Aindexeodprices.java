package com.example.demo2.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Aindexeodprices implements Serializable {
    private String sInfoWindcode;

    private String objectId;

    private String tradeDt;

    private String crncyCode;

    private BigDecimal sDqPreclose;

    private BigDecimal sDqOpen;

    private BigDecimal sDqHigh;

    private BigDecimal sDqLow;

    private BigDecimal sDqClose;

    private BigDecimal sDqChange;

    private BigDecimal sDqPctchange;

    private BigDecimal sDqVolume;

    private BigDecimal sDqAmount;

    private String secId;

    private static final Long serialVersionUID = 1L;
}