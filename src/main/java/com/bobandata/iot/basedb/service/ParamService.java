package com.bobandata.iot.basedb.service;

import com.bobandata.iot.entity.dms.Param;
import com.bobandata.iot.entity.dms.Protocol;
import com.bobandata.iot.entity.dms.Pulse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:16 2018/7/18.
 */
public interface ParamService extends BaseService<Param, Integer> {

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return
     */
    Page<Param> selectPageList(Pageable pageable);

    List<Param> findTypes();
    /**
     * 分类查询
     * */
    Map<Integer,List<Param>> paramSortByType();

    List<Param> findSimilar(String ParamName);
}
