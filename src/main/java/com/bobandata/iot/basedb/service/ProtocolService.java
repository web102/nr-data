package com.bobandata.iot.basedb.service;

import com.bobandata.iot.entity.dms.Protocol;
import com.bobandata.iot.util.SimpleTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


/**
 * @Author: liutuo
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:17 2018/7/16.
 */
public interface ProtocolService extends BaseService<Protocol, Integer>{

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return
     */
    Page<Protocol> selectPageList(Pageable pageable);

    SimpleTree protocolTree();

    SimpleTree modelProtocolTree();

    Map<String,List<Protocol>>protocolSort();

    List<Protocol> findByProtocolName(String protocolName);

    List<Protocol> findSimilar(String protocolName);
}
