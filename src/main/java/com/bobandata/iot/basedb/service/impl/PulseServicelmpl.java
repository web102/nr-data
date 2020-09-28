package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.entity.dms.Param;
import com.bobandata.iot.entity.dms.Pulse;
import com.bobandata.iot.basedb.repository.ParamRepository;
import com.bobandata.iot.basedb.repository.PulseRepository;
import com.bobandata.iot.basedb.service.ParamService;
import com.bobandata.iot.basedb.service.PulseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:43 2018/7/17.
 */
@Transactional
@Service
public class PulseServicelmpl extends BaseServiceImpl<Pulse, Integer> implements PulseService {

    @Autowired
    private PulseRepository pulseRepository;

    @Autowired
    private ParamService paramService;

    @Override
    public Page<Pulse> selectPageList(Pageable pageable) {
        return pulseRepository.selectPageList(pageable);
    }

    @Autowired
    private ParamRepository paramRepository;
    @Override
    public boolean toLeadPulse(Integer meterId, Integer paramModelId) {
        //查询参数模板对应的参数
        List<Param> params = paramRepository.findParams(paramModelId);
        ArrayList<Pulse> pulses = new ArrayList<>();
        for (int i = 0; i < params.size(); i++) {
            Param param = params.get(i);
            Pulse pulse = new Pulse();
            pulse.setPulseName(param.getParamName());
            pulse.setPulseType(param.getParamType());
            pulse.setIsLimitValid(param.getIsLimitValid());
            pulse.setLimitValidInterval(param.getLimitValidInterval());
            pulse.setLowLimit(param.getLowLimit());
            pulse.setUpLimit(param.getUpLimit());
            pulse.setLimitValidTimeTag(param.getLimitValidTimeTag());
            pulse.setPulseAddr(i+1);//暂定
            pulse.setMeterId(meterId);
            pulses.add(pulse);
        }
        List<Pulse> save = pulseRepository.save(pulses);
        if (save != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addsByMeterIdAndParamIds(List<Integer> paramIds) {
        Integer meterId = paramIds.get(0);
        paramIds.remove(0);
        Integer i=0;
        for(Integer paramId : paramIds){
            i++;
            Param param = paramService.findOne(paramId);
            Pulse pulse = new Pulse();
            pulse.setPulseName(param.getParamName());
            pulse.setPulseType(param.getParamType());
            pulse.setIsLimitValid(param.getIsLimitValid());
            pulse.setLimitValidInterval(param.getLimitValidInterval());
            pulse.setLowLimit(param.getLowLimit());
            pulse.setUpLimit(param.getUpLimit());
            pulse.setLimitValidTimeTag(param.getLimitValidTimeTag());
            pulse.setPulseAddr(i);//暂定
            pulse.setMeterId(meterId);
            pulseRepository.save(pulse);
        }
    }

    @Override
    public List<Pulse> findByMeterId(Integer meterId) {
        return pulseRepository.findByMeterId(meterId);
    }

    @Override
    public List<Pulse> findSimilar(String pulseName,Integer meterId) {
        List<Pulse> all = pulseRepository.findByMeterId(meterId);

        List<Pulse> similar = new ArrayList<>();
        List<Pulse> identical = new ArrayList<>();
        List<Pulse> start = new ArrayList<>();
        List<Pulse> indexOf = new ArrayList<>();

        for(Pulse pulse : all){
            if(pulse.getPulseName().equals(pulseName)){
                identical.add(pulse);
            }
            else if(pulse.getPulseName().startsWith(pulseName)){
                start.add(pulse);
            }
            else if(pulse.getPulseName().indexOf(pulseName)!=-1){
                indexOf.add(pulse);
            }
        }
        similar.addAll(identical);
        similar.addAll(start);
        similar.addAll(indexOf);
        return similar;
    }

    @Override
    public void deleteByMeterId(Integer meterId) {
        pulseRepository.deleteByMeterId(meterId);
    }
}