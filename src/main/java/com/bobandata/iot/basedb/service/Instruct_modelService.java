package com.bobandata.iot.basedb.service;

import com.bobandata.iot.basedb.bean.Instruct_model;
import com.bobandata.iot.basedb.bean.SimpleTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:07 2018/7/17.
 */
public interface Instruct_modelService extends BaseService<Instruct_model, Integer>{

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return
     */
    Page<Instruct_model> selectPageList(Pageable pageable);

    //以模板为单位生产指令树
    SimpleTree instructTree();

    List<Instruct_model> findByModelName(String modelName);

    List<Instruct_model> findSimilar(String modelName);
}

