package com.bobandata.iot.basedb.bean;

import java.io.Serializable;

public class TaskParam implements Serializable {
    private int startMark;
    private int endMark;
    private String startDate;
    private String endDate;
    private byte taskType;
    private String ipAddress;
    private int ipPort;
    private String restPath;
    private String LinkAddress;

    public int getStartMark() {
        return this.startMark;
    }

    public void setStartMark(int startMark) {
        this.startMark = startMark;
    }

    public int getEndMark() {
        return this.endMark;
    }

    public void setEndMark(int endMark) {
        this.endMark = endMark;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public byte getTaskType() {
        return this.taskType;
    }

    public void setTaskType(byte taskType) {
        this.taskType = taskType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getIpPort() {
        return ipPort;
    }

    public void setIpPort(int ipPort) {
        this.ipPort = ipPort;
    }

    public String getRestPath() {
        return restPath;
    }

    public void setRestPath(String restPath) {
        this.restPath = restPath;
    }

    public String getLinkAddress() {
        return LinkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        LinkAddress = linkAddress;
    }
}