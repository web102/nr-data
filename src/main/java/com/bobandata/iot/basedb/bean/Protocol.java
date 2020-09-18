package com.bobandata.iot.basedb.bean;

import javax.persistence.*;

/**
 * @Author: liutuo
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:03 2018/7/16.
 */
@Entity
public class Protocol{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer protocolId;
    @Column(name="PROTOCOL_NAME")
    private String protocolName;
    @Column(name="PROTOCOL_ALIAS")
    private String protocolAlias;
    @Column(name="REST_PATH")
    private String restPath;
    @Column(name="VERSION_NAME")
    private String versionName;
    @Column(name="PROTOCOL_MODEL_ID")
    private Integer protocolModelId;
    @Column(name = "LINK_ADDRESS")
    private String linkAddress;

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getProtocolAlias() {
        return protocolAlias;
    }

    public void setProtocolAlias(String protocolAlias) {
        this.protocolAlias = protocolAlias;
    }

    public String getRestPath() {
        return restPath;
    }

    public void setRestPath(String restPath) {
        this.restPath = restPath;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getProtocolModelId() {
        return protocolModelId;
    }

    public void setProtocolModelId(Integer protocolModelId) {
        this.protocolModelId = protocolModelId;
    }

    public String getLinkAddress() {return linkAddress;}

    public void setLinkAddress(String linkAddress) {this.linkAddress = linkAddress;}
}
