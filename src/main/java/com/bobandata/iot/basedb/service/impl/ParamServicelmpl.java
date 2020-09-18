package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.bean.Param;
import com.bobandata.iot.basedb.bean.Param_model;
import com.bobandata.iot.basedb.bean.Pulse;
import com.bobandata.iot.basedb.repository.ParamRepository;
import com.bobandata.iot.basedb.repository.PulseRepository;
import com.bobandata.iot.basedb.service.ParamService;
import com.bobandata.iot.basedb.service.PulseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:25 2018/7/18.
 */
@Service
public class ParamServicelmpl extends BaseServiceImpl<Param, Integer> implements ParamService {

    @Autowired
    private ParamRepository paramRepository;


    @Override
    public Page<Param> selectPageList(Pageable pageable) {
        return paramRepository.selectPageList(pageable);
    }

    @Override
    public List<Param> findTypes() {
        return paramRepository.findTypes();
    }

    @Override
    public Map<Integer, List<Param>> paramSortByType() {
        HashMap<Integer, List<Param>> paramIntegerListHashMap = new HashMap<>();

        List<Param> types = paramRepository.findTypes();
        for (Param p : types) {
            paramIntegerListHashMap.put(p.getParamType(), new ArrayList<>());
        }
        List<Param> params = paramRepository.findAll();
        for (Param p : params) {
            paramIntegerListHashMap.get(p.getParamType()).add(p);
        }
        return paramIntegerListHashMap;
    }

    @Override
    public List<Param> findSimilar(String paramName) {
        List<Param> all = paramRepository.findAll();

        List<Param> similar = new ArrayList<>();
        List<Param> identical = new ArrayList<>();
        List<Param> start = new ArrayList<>();
        List<Param> indexOf = new ArrayList<>();

        for(Param Param : all){
            if(Param.getParamName().equals(paramName)){
                identical.add(Param);
            }
            else if(Param.getParamName().startsWith(paramName)){
                start.add(Param);
            }
            else if(Param.getParamName().indexOf(paramName)!=-1){
                indexOf.add(Param);
            }
        }
        similar.addAll(identical);
        similar.addAll(start);
        similar.addAll(indexOf);
        return similar;
    }
}
