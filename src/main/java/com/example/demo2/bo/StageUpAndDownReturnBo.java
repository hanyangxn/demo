package com.example.demo2.bo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class StageUpAndDownReturnBo {
    private String tradeDt;
    private List<StageUpAndDowns> stageUpAndDownList;

    public String getTradeDt() {
        return tradeDt;
    }

    public void setTradeDt(String tradeDt) {
        this.tradeDt = tradeDt;
    }

    public List<StageUpAndDowns> getStageUpAndDownList() {
        return stageUpAndDownList;
    }

    public void setStageUpAndDownList(List<StageUpAndDowns> stageUpAndDownList) {
        this.stageUpAndDownList = stageUpAndDownList;
    }

    public static class StageUpAndDowns{
        private String name;
        private List<Map<String,?>> stageUpAndDownsList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Map<String, ?>> getStageUpAndDownsList() {
            return stageUpAndDownsList;
        }

        public void setStageUpAndDownsList(List<Map<String, ?>> stageUpAndDownsList) {
            this.stageUpAndDownsList = stageUpAndDownsList;
        }
    }


}
