package com.bobandata.iot.basedb.service;

import com.bobandata.iot.basedb.bean.Instruct;
import com.bobandata.iot.basedb.bean.Instruct_model_set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:59 2018/7/17.
 */
public interface Instruct_model_setService extends BaseService<Instruct_model_set, Integer> {

    Page<Instruct_model_set> selectPageList(Pageable pageable);

    //在instruct_model_set表 找到modelId对应的所有指令id
    List<Integer> findInstructIdByModelId(Integer modelId);

    //通过modelId找到modelId对应的所有指令id
    List<Instruct>modelInstruct(Integer modelId);

    //添加模板指令表
    List<Instruct_model_set> addInstructModelSetData(Integer modelId, Integer[] instructIds);

    void deleteByModelIdAndInstructId(Integer modelId, Integer instructId);

    void deleteByModelId(Integer modelId);

    void deleteByInstructId(Integer instructId);
}
