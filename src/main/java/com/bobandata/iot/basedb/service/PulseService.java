package com.bobandata.iot.basedb.service;

import com.bobandata.iot.basedb.bean.Pulse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:35 2018/7/17.
 */
public interface PulseService extends BaseService<Pulse, Integer> {

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return
     */
    Page<Pulse> selectPageList(Pageable pageable);

    List<Pulse> findByMeterId(Integer meterId);

    List<Pulse> findSimilar(String pulseName, Integer meterId);

    void deleteByMeterId(Integer meterId);

    boolean toLeadPulse(Integer meterId, Integer paramModelId);

    void addsByMeterIdAndParamIds(List<Integer> paramIds);
}
