package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.repository.MeterRepository;
import com.bobandata.iot.basedb.service.MeterService;
import com.bobandata.iot.entity.dms.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:42 2018/7/17.
 */
@Service
public class MeterServicelmpl extends BaseServiceImpl<Meter, Integer> implements MeterService {

    @Autowired
    private MeterRepository meterRepository;


    @Override
    public void deleteByErtuId(Integer ertuId) {
        meterRepository.deleteByErtuId(ertuId);
    }

    @Override
    public Page<Meter> selectPageList(Pageable pageable) {
        return meterRepository.selectPageList(pageable);
    }


    @Override
    public List<Meter> findMeterByErtuId(Integer ertuId) {
        return meterRepository.findByErtuId(ertuId);
    }


    @Override
    public List<Meter> findSimilar(String meterName,Integer ertuId){
        List<Meter> all = findMeterByErtuId(ertuId);
        List<Meter> similar = new ArrayList<>();
        List<Meter> identical = new ArrayList<>();
        List<Meter> start = new ArrayList<>();
        List<Meter> indexOf = new ArrayList<>();

        for(Meter meter : all){
            if(meter.getMeterName().equals(meterName)){
                identical.add(meter);
            }
            else if(meter.getMeterName().startsWith(meterName)){
                start.add(meter);
            }
            else if(meter.getMeterName().indexOf(meterName)!=-1){
                indexOf.add(meter);
            }
        }
        similar.addAll(identical);
        similar.addAll(start);
        similar.addAll(indexOf);
        return similar;
    }
}
