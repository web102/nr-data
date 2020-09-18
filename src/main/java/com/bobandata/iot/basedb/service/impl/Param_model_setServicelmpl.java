package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.bean.Param_model_set;
import com.bobandata.iot.basedb.repository.Param_model_setRepository;
import com.bobandata.iot.basedb.service.Param_model_setService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:26 2018/7/18.
 */
@Service
public class Param_model_setServicelmpl extends BaseServiceImpl<Param_model_set, Integer> implements Param_model_setService {

    @Autowired
    private Param_model_setRepository param_model_setRepository;

    @Override
    public boolean saveRelation(Integer modelId, Integer[] paramIds) {
        ArrayList<Param_model_set> param_model_sets = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(paramIds));
        for (int i = 0; i < integers.size(); i++) {
            Param_model_set param_model_set = new Param_model_set();
            param_model_set.setModelId(modelId);
            param_model_set.setParamId(integers.get(i));
            param_model_sets.add(param_model_set);
        }
        List<Param_model_set> save = param_model_setRepository.save(param_model_sets);

        if (save.size() >0 ){
            return true;
        }
        return false;
    }

    @Override
    public List<Integer> findParamIdByModelId(Integer modelId) {
        return param_model_setRepository.findParamIdByModelId(modelId);
    }

    @Override
    public void deleteParamIdByModelId(Integer modelId) {
        param_model_setRepository.deleteParamIdByModelId(modelId);
    }
}
