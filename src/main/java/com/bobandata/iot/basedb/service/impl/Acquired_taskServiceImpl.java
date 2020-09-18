package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.bean.Acquired_task;
import com.bobandata.iot.basedb.repository.Acquired_taskRepository;
import com.bobandata.iot.basedb.service.Acquired_taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:14 2018/7/17.
 */

@Service
public class Acquired_taskServiceImpl extends BaseServiceImpl<Acquired_task, Integer> implements Acquired_taskService {

    @Autowired
    private Acquired_taskRepository acquired_taskRepository;

    @Override
    public Page<Acquired_task> selectPageList(Pageable pageable) {
        return acquired_taskRepository.selectPageList(pageable);
    }
}
