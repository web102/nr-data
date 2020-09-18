package com.bobandata.iot.basedb.bean;

import javax.persistence.*;

/**
 * @Author: zhanglingzhi
 * @Description:    电表表
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:09 2018/7/17.
 */
@Entity
public class Meter {
//    METER_ID	电表ID	Int	10	是
//    METER_NAME	名称	varchar	20	否
//    METER_ADDR	电表SN	varchar	20	否
//    MAIN_DEPUTY	主副标识	int	10	否
//    TARIFF	费率	varchar	20	否
//    DECIMAL	电表小数位	int	10	否
//    PORT	通道号	int	10	否
//    CT	CT	varchar	20	否
//    PT	PT	varchar	20	否
//    ERTU_ID	终端ID	int	10	否
//    PROTOCOL_ID	规约ID	int	10	否
//    ACQUIRED_ID	任务ID	int	10	否
//    MANUFACTURE_ID	设备厂家	int	10	否
//    MODEL_ID	设备型号	int	10	否
//    STATE	通讯状态	int	10	否

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer meterId;
    @Column(name = "METER_NAME")
    private String meterName;
    @Column(name = "METER_ADDR")
    private String meterAddr;
    @Column(name = "MAIN_DEPUTY")
    private Integer mainDeputy;
    @Column(name = "TARIFF")
    private String tariff;
   // @Column(name = "DECIMAL")
    //private Integer decimal;
    @Column(name = "PORT")
    private Integer port;
    @Column(name = "CT")
    private String ct;
    @Column(name = "PT")
    private String pt;
    @Column(name = "ERTU_ID")
    private Integer ertuId;
    @Column(name = "PROTOCOL_ID")
    private Integer protocolId;
    @Column(name = "ACQUIRED_ID")
    private Integer acquiredId;
    @Column(name = "MANUFACTURE_ID")
    private Integer manufactureId;
    @Column(name = "MODEL_ID")
    private Integer modelId;
    @Column(name = "STATE")
    private Integer state;
    @Column(name = "PARAM_MODEL_ID")
    private Integer paramModelId;

    public Integer getParamModelId() {
        return paramModelId;
    }

    public void setParamModelId(Integer paramModelId) {
        this.paramModelId = paramModelId;
    }

    public Integer getMeterId() {
        return meterId;
    }

    public void setMeterId(Integer meterId) {
        this.meterId = meterId;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public String getMeterAddr() {
        return meterAddr;
    }

    public void setMeterAddr(String meterAddr) {
        this.meterAddr = meterAddr;
    }

    public Integer getMainDeputy() {
        return mainDeputy;
    }

    public void setMainDeputy(Integer mainDeputy) {
        this.mainDeputy = mainDeputy;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

/*    public Integer getDecimal() { return decimal; }

    public void setDecimal(Integer decimal) { this.decimal = decimal; }*/

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public Integer getErtuId() {
        return ertuId;
    }

    public void setErtuId(Integer ertuId) {
        this.ertuId = ertuId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
