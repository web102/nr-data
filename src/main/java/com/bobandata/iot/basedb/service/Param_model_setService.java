package com.bobandata.iot.basedb.service;

import com.bobandata.iot.basedb.bean.Param_model;
import com.bobandata.iot.basedb.bean.Param_model_set;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:16 2018/7/18.
 */
public interface Param_model_setService extends BaseService<Param_model_set, Integer> {

    boolean saveRelation(Integer modelId, Integer[] paramIds);

    List<Integer> findParamIdByModelId(Integer modelId);

    void deleteParamIdByModelId(Integer modelId);

}
