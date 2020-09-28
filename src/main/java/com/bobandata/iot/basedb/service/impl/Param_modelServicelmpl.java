package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.entity.dms.Param_model;
import com.bobandata.iot.basedb.repository.Param_modelRepository;
import com.bobandata.iot.basedb.service.Param_modelService;
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
 * @Date: Created in 17:26 2018/7/18.
 */
@Service
public class Param_modelServicelmpl extends BaseServiceImpl<Param_model, Integer> implements Param_modelService {

    @Autowired
    private Param_modelRepository param_modelRepository;


    @Override
    public Page<Param_model> selectPageList(Pageable pageable) {
        return param_modelRepository.selectPageList(pageable);
    }

    @Override
    public List<Param_model> findSimilar(String modelName) {
        List<Param_model> all = param_modelRepository.findAll();

        List<Param_model> similar = new ArrayList<>();
        List<Param_model> identical = new ArrayList<>();
        List<Param_model> start = new ArrayList<>();
        List<Param_model> indexOf = new ArrayList<>();

        for(Param_model Param_model : all){
            if(Param_model.getModelName().equals(modelName)){
                identical.add(Param_model);
            }
            else if(Param_model.getModelName().startsWith(modelName)){
                start.add(Param_model);
            }
            else if(Param_model.getModelName().indexOf(modelName)!=-1){
                indexOf.add(Param_model);
            }
        }
        similar.addAll(identical);
        similar.addAll(start);
        similar.addAll(indexOf);
        return similar;
    }
}
