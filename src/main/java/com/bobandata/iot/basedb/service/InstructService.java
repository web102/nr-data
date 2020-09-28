package com.bobandata.iot.basedb.service;

import com.bobandata.iot.entity.dms.Instruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 10:52 2018/7/17.
 */
public interface InstructService extends BaseService<Instruct, Integer>{

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return
     */
    Page<Instruct> selectPageList(Pageable pageable);

    //分类展示所有指令
    Map<String,List<Instruct>> instructSortByType(List<Instruct> instructs);

    List<Instruct> findByInstructName(String instructName);

    List<Instruct> findSimilar(String instructName, Integer protocolId);
}
