package com.bobandata.iot.basedb.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:06 2018/7/17.
 */

@Entity
public class Acquired_task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskId;

    @Column
    private String taskName;
    @Column
    private Integer instructId;
    @Column
    private Integer taskInterval;
    @Column
    private Integer initialDelay;
    @Column
    private Date period;
    @Column
    private String acquiredIds;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getInstructId() {
        return instructId;
    }

    public void setInstructId(Integer instructId) {
        this.instructId = instructId;
    }

    public Integer getTaskInterval() {
        return taskInterval;
    }

    public void setTaskInterval(Integer taskInterval) {
        this.taskInterval = taskInterval;
    }

    public Integer getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(Integer initialDelay) {
        this.initialDelay = initialDelay;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public String getAcquiredIds() {
        return acquiredIds;
    }

    public void setAcquiredIds(String acquiredIds) {
        this.acquiredIds = acquiredIds;
    }
}
