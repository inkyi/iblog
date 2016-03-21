package com.inkyi.iblog.entity;

import java.util.Date;

public class IbMsg {
    private Integer id;

    private Integer inUid;

    private Integer sendUid;

    private String content;

    private Date pubTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInUid() {
        return inUid;
    }

    public void setInUid(Integer inUid) {
        this.inUid = inUid;
    }

    public Integer getSendUid() {
        return sendUid;
    }

    public void setSendUid(Integer sendUid) {
        this.sendUid = sendUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }
}