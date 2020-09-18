package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.bean.Meter;

import com.bobandata.iot.basedb.bean.Param;
import com.bobandata.iot.basedb.bean.Pulse;
import com.bobandata.iot.basedb.repository.MeterRepository;
import com.bobandata.iot.basedb.repository.ParamRepository;
import com.bobandata.iot.basedb.repository.PulseRepository;
import com.bobandata.iot.basedb.service.MeterService;
import com.bobandata.iot.basedb.service.PulseService;
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

    @Autowired
    private ParamRepository paramRepository;

    @Autowired
    private PulseRepository pulseRepository;

    @Autowired
    private MeterService meterService;

    @Autowired
    private PulseService pulseService;

    @Override
    public Page<Meter> selectPageList(Pageable pageable) {
        return meterRepository.selectPageList(pageable);
    }


    @Override
    public List<Meter> findMeterByErtuId(Integer ertuId) {
        List<Meter> meters = new ArrayList<>();

        List<Meter> meterAll = meterRepository.findAll();
        for(Meter meter : meterAll){
            if(meter.getErtuId()==ertuId){
                meters.add(meter);
            }
        }
        return meters;
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
