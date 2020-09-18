package com.bobandata.iot.basedb.bean;

import javax.persistence.*;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 14:37 2018/7/17.
 */

@Entity
public class Instruct_model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer modelId;

    @Column
    private String modelName;
    @Column
    private String modelDesc;

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }
}
