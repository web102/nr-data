package com.bobandata.iot.basedb.service;

import com.bobandata.iot.entity.dms.Acquired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:45 2018/7/17.
 */
public interface AcquiredService extends BaseService<Acquired, Integer> {

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return
     */
    Page<Acquired> selectPageList(Pageable pageable);

}
