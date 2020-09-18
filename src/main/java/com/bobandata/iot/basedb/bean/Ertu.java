package com.bobandata.iot.basedb.bean;

import javax.persistence.*;

/**
 * @Author: zhanglingzhi
 * @Description:    终端表
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 14:55 2018/7/17.
 */
@Entity
public class Ertu {

    //    ERTU_ID	终端ID	Int	10	是
//    ERTU_NAME	场站名称	varchar	20	否
//    VOLTAGE_CLASS	电压等级	varchar	20	否
//    MANUFACTURE_ID	设备厂家	int	10	否
//    MODEL_ID	设备型号	int	10	否
//    ERTU_TYPE	场站类型	int	6	否
//    PROTOCOL_ID	规约ID	int	10	否
//    ACQUIRED_ID	任务ID	int	10	否
//    ADDRESS	地址	varchar	100	否
//    LONGITUDE	经度	double		否
//    LATITUDE	纬度	double		否
//    STORE_CAP	存储容量	int	20	否
//    IS_IN_USE	是否使用中	int	10	否

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ertuId;
    @Column(name = "ERTU_NAME")
    private String ertuName;
    @Column(name = "VOLTAGE_CLASS")
    private String voltageClass;
    @Column(name = "MANUFACTURE_ID")
    private Integer manufactureId;
    @Column(name = "MODEL_ID")
    private Integer modelId;
    @Column(name = "ERTU_TYPE")
    private Integer ertuType;
    @Column(name = "PROTOCOL_ID")
    private Integer protocolId;
    @Column(name = "ACQUIRED_ID")
    private Integer acquiredId;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "LONGITUDE")
    private double longitude;
    @Column(name = "LATITUDE")
    private double latitude;
    @Column(name = "STORE_CAP")
    private Integer storeCap;
    @Column(name = "IS_IN_USE")
    private Integer isInUse;

    public Integer getErtuId() {
        return ertuId;
    }

    public void setErtuId(Integer ertuId) {
        this.ertuId = ertuId;
    }

    public String getErtuName() {
        return ertuName;
    }

    public void setErtuName(String ertuName) {
        this.ertuName = ertuName;
    }

    public String getVoltageClass() {
        return voltageClass;
    }

    public void setVoltageClass(String voltageClass) {
        this.voltageClass = voltageClass;
    }

    public Integer getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(Integer manufactureId) {
        this.manufactureId = manufactureId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getErtuType() {
        return ertuType;
    }

    public void setErtuType(Integer ertuType) {
        this.ertuType = ertuType;
    }

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public Integer getAcquiredId() {
        return acquiredId;
    }

    public void setAcquiredId(Integer acquiredId) {
        this.acquiredId = acquiredId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Integer getStoreCap() {
        return storeCap;
    }

    public void setStoreCap(Integer storeCap) {
        this.storeCap = storeCap;
    }

    public Integer getIsInUse() {
        return isInUse;
    }

    public void setIsInUse(Integer isInUse) {
        this.isInUse = isInUse;
    }
}
