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

    public String getsInfoWindcode() {
        return sInfoWindcode;
    }

    public void setsInfoWindcode(String sInfoWindcode) {
        this.sInfoWindcode = sInfoWindcode;
    }

    public String getfPrtEnddate() {
        return fPrtEnddate;
    }

    public void setfPrtEnddate(String fPrtEnddate) {
        this.fPrtEnddate = fPrtEnddate;
    }

    public String getCrncyCode() {
        return crncyCode;
    }

    public void setCrncyCode(String crncyCode) {
        this.crncyCode = crncyCode;
    }

    public String getsInfoStockwindcode() {
        return sInfoStockwindcode;
    }

    public void setsInfoStockwindcode(String sInfoStockwindcode) {
        this.sInfoStockwindcode = sInfoStockwindcode;
    }

    public BigDecimal getfPrtStkvalue() {
        return fPrtStkvalue;
    }

    public void setfPrtStkvalue(BigDecimal fPrtStkvalue) {
        this.fPrtStkvalue = fPrtStkvalue;
    }

    public BigDecimal getfPrtStkquantity() {
        return fPrtStkquantity;
    }

    public void setfPrtStkquantity(BigDecimal fPrtStkquantity) {
        this.fPrtStkquantity = fPrtStkquantity;
    }

    private BigDecimal fPrtStkquantity;

    @Override
    public String toString() {
        return "Chinamutualfundstockportfolio{" +
                "sInfoWindcode='" + sInfoWindcode + '\'' +
                ", fPrtEnddate='" + fPrtEnddate + '\'' +
                ", crncyCode='" + crncyCode + '\'' +
                ", sInfoStockwindcode='" + sInfoStockwindcode + '\'' +
                ", fPrtStkvalue=" + fPrtStkvalue +
                ", fPrtStkquantity=" + fPrtStkquantity +
                '}';
    }

    private static final long serialVersionUID = 1L;
}