package com.bobandata.iot.basedb.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:38 2018/7/17.
 */

@Entity
public class Acquired {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer acquiredId;

    @Column()
    private String acquiredName;
    @Column
    private Integer acquiredInterval;
    @Column
    private Integer initialDelay;
    @Column
    private Integer curStatus;
    @Column
    private Integer address;
    @Column
    private Integer pri;
    @Column
    private Date lastAcquireTime;
    @Column
    private Integer commMode;

    public Integer getAcquiredId() {
        return acquiredId;
    }

    public void setAcquiredId(Integer acquiredId) {
        this.acquiredId = acquiredId;
    }

    public String getAcquiredName() {
        return acquiredName;
    }

    public void setAcquiredName(String acquiredName) {
        this.acquiredName = acquiredName;
    }

    public Integer getAcquiredInterval() {
        return acquiredInterval;
    }

    public void setAcquiredInterval(Integer acquiredInterval) {
        this.acquiredInterval = acquiredInterval;
    }

    public Integer getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(Integer initialDelay) {
        this.initialDelay = initialDelay;
    }

    public Integer getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(Integer curStatus) {
        this.curStatus = curStatus;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Integer getPri() {
        return pri;
    }

    public void setPri(Integer pri) {
        this.pri = pri;
    }

    public Date getLastAcquireTime() {
        return lastAcquireTime;
    }

    public void setLastAcquireTime(Date lastAcquireTime) {
        this.lastAcquireTime = lastAcquireTime;
    }

    public Integer getCommMode() {
        return commMode;
    }

    public void setCommMode(Integer commMode) {
        this.commMode = commMode;
    }
}
