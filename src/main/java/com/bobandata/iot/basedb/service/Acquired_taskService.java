package com.bobandata.iot.basedb.service;

import com.bobandata.iot.basedb.bean.Acquired_task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:14 2018/7/17.
 */
public interface Acquired_taskService extends BaseService<Acquired_task, Integer>  {

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return
     */
    Page<Acquired_task> selectPageList(Pageable pageable);
}
