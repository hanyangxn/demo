package com.hy.demo.testReflectionUtil.bo;

import com.hy.demo.testReflectionUtil.enums.DateType;
import com.hy.demo.testReflectionUtil.enums.ServiceType;

import java.io.Serializable;

public class RequestBo implements Serializable {
    private ServiceType type;
    private DateType dateType;

    public RequestBo(ServiceType type, DateType dateType) {
        this.type = type;
        this.dateType = dateType;
    }

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public DateType getDateType() {
        return dateType;
    }

    public void setDateType(DateType dateType) {
        this.dateType = dateType;
    }
}
