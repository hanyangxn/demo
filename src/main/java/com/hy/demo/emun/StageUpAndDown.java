package com.hy.demo.emun;

public enum StageUpAndDown {

    THE_FUND("1","本基金"),
    Average_Same_Kind("2","同类平均"),
    SH_300("3","深沪300"),
    Similar_Ranking("4","同类排名"),
    Similar_Changes("5","同类变动"),
    Third_Ranking("6","三分为排名");

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    StageUpAndDown(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
