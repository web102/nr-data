package com.bobandata.iot.basedb.service;

import com.bobandata.iot.basedb.bean.Param_model;
import com.bobandata.iot.basedb.bean.Param_model_set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:16 2018/7/18.
 */
public interface Param_modelService extends BaseService<Param_model, Integer> {

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return
     */
    Page<Param_model> selectPageList(Pageable pageable);

    List<Param_model> findSimilar(String ParamName);
}
