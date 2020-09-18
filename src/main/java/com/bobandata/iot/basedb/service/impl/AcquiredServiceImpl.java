package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.bean.Acquired;
import com.bobandata.iot.basedb.repository.AcquiredRepository;
import com.bobandata.iot.basedb.service.AcquiredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:47 2018/7/17.
 */
@Service
public class AcquiredServiceImpl  extends BaseServiceImpl<Acquired, Integer> implements AcquiredService{

    @Autowired
    private AcquiredRepository acquiredRepository;

    @Override
    public Page<Acquired> selectPageList(Pageable pageable) {
        return acquiredRepository.selectPageList(pageable);
    }
}
