package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.repository.NetworkRepository;
import com.bobandata.iot.basedb.service.NetworkService;
import com.bobandata.iot.entity.dms.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:43 2018/7/17.
 */
@Service
public class NetworkServicelmpl extends BaseServiceImpl<Network, Integer> implements NetworkService {

    @Autowired
    private NetworkRepository networkRepository;


    @Override
    public void deleteByErtuId(Integer ertuId) {
        networkRepository.deleteByErtuId(ertuId);
    }

    @Override
    public Page<Network> selectPageList(Pageable pageable) {
        return networkRepository.selectPageList(pageable);
    }

    @Override
    public List<Network> findNetworkByErtuId(Integer ertuId) {
        return networkRepository.findByErtuId(ertuId);
    }
}