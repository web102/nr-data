package com.bobandata.iot.basedb.bean;

import javax.persistence.*;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 10:29 2018/7/17.
 */

@Entity
public class Instruct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer instructId;

    @Column(name="INSTRUCT_NAME")
    private String instructName;
    @Column(name="INSTRUCT_TYPE")
    private String instructType;
    @Column(name="INSTRUCT_PATH")
    private Integer instructPath;

    public Integer getInstructId() {
        return instructId;
    }

    public void setInstructId(Integer instructId) {
        this.instructId = instructId;
    }

    public String getInstructName() {
        return instructName;
    }

    public void setInstructName(String instructName) {
        this.instructName = instructName;
    }

    public String getInstructType() {
        return instructType;
    }

    public void setInstructType(String instructType) {
        this.instructType = instructType;
    }

    public Integer getInstructPath() {
        return instructPath;
    }

    public void setInstructPath(Integer instructPath) {
        this.instructPath = instructPath;
    }

}
