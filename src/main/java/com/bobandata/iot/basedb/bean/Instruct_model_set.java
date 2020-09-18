package com.bobandata.iot.basedb.bean;

import javax.persistence.*;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:54 2018/7/17.
 */

@Entity
public class Instruct_model_set {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer modelId;
    @Column
    private Integer instructId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getInstructId() {
        return instructId;
    }

    public void setInstructId(Integer instructId) {
        this.instructId = instructId;
    }

}
