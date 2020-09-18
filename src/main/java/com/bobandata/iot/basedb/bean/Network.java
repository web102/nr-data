package com.bobandata.iot.basedb.bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @Author: zhanglingzhi
 * @Description:    通道表（网络通信参数）
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:23 2018/7/17.
 */
@Entity
public class Network {

//    CHANNEL_ID	通道ID	Int	20	是
//    CHANNEL_NAME	通道名称	varchar	100	否
//    STATUS	状态	int	10	否
//    IP_ADDRESS	IP地址	varchar	20	否
//    IP_PORT	访问端口	Int	10	否
//    TRY_TIMES	尝试了多久	int	10	否
//    COMM_TIME_TAG	上一次尝试时间	timestamp		否
//    MAX_COMM_DELAY	最大延迟时间	int	10	否
//    IS_IN_USE	是否使用中	int	10	否
//    PRI	优先级	decimal	(10,0)	否
//    LAST_SUCCESS_TIME_TAG	最后一次链接时间	timestamp		否
//    ACQUIRED_ID	采集任务ID	Int	20	否
//    ERTU_ID	终端ID	Int	20	否
//    NETWORK_CARD	网卡	varchar	10	否

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer channelId;
    @Column(name = "CHANNEL_NAME")
    private String channelName;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "IP_ADDRESS")
    private String ipAddress;
    @Column(name = "IP_PORT")
    private Integer ipPort;
    @Column(name = "TRY_TIMES")
    private Integer tryTimes;
    @Column(name = "COMM_TIME_TAG")
    private Date commTimeTag;
    @Column(name = "MAX_COMM_DELAY")
    private Integer maxCommDelay;
    @Column(name = "IS_IN_USE")
    private Integer isInUse;
    @Column(name = "PRI")
    private BigDecimal pri;
    @Column(name = "LAST_SUCCESS_TIME_TAG")
    private Date lastSuccessTimeTag;
    @Column(name = "ACQUIRED_ID")
    private Integer acquiredId;
    @Column(name = "ERTU_ID")
    private Integer ertuId;
    @Column(name = "NETWORK_CARD")
    private String networkCard;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getIpPort() {
        return ipPort;
    }

    public void setIpPort(Integer ipPort) {
        this.ipPort = ipPort;
    }

    public Integer getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(Integer tryTimes) {
        this.tryTimes = tryTimes;
    }

    public Date getCommTimeTag() {
        return commTimeTag;
    }

    public void setCommTimeTag(Date commTimeTag) {
        this.commTimeTag = commTimeTag;
    }

    public Integer getMaxCommDelay() {
        return maxCommDelay;
    }

    public void setMaxCommDelay(Integer maxCommDelay) {
        this.maxCommDelay = maxCommDelay;
    }

    public Integer getIsInUse() {
        return isInUse;
    }

    public void setIsInUse(Integer isInUse) {
        this.isInUse = isInUse;
    }

    public BigDecimal getPri() {
        return pri;
    }

    public void setPri(BigDecimal pri) {
        this.pri = pri;
    }

    public Date getLastSuccessTimeTag() {
        return lastSuccessTimeTag;
    }

    public void setLastSuccessTimeTag(Date lastSuccessTimeTag) {
        this.lastSuccessTimeTag = lastSuccessTimeTag;
    }

    public Integer getAcquiredId() {
        return acquiredId;
    }

    public void setAcquiredId(Integer acquiredId) {
        this.acquiredId = acquiredId;
    }

    public Integer getErtuId() {
        return ertuId;
    }

    public void setErtuId(Integer ertuId) {
        this.ertuId = ertuId;
    }

    public String getNetworkCard() {
        return networkCard;
    }

    public void setNetworkCard(String networkCard) {
        this.networkCard = networkCard;
    }
}
