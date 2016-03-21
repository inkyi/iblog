package com.inkyi.iblog.entity;

public class IbStatistics {
    private Integer id;

    private Integer uid;

    private Integer todayAccess;

    private Integer sunAccess;

    private Integer integral;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTodayAccess() {
        return todayAccess;
    }

    public void setTodayAccess(Integer todayAccess) {
        this.todayAccess = todayAccess;
    }

    public Integer getSunAccess() {
        return sunAccess;
    }

    public void setSunAccess(Integer sunAccess) {
        this.sunAccess = sunAccess;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}