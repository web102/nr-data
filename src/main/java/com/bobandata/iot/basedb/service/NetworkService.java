package com.bobandata.iot.basedb.service;

import com.bobandata.iot.basedb.bean.Meter;
import com.bobandata.iot.basedb.bean.Network;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:34 2018/7/17.
 */
public interface NetworkService extends BaseService<Network, Integer> {

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return
     */
    Page<Network> selectPageList(Pageable pageable);

    List<Network> findNetworkByErtuId(Integer ertuId);

}
