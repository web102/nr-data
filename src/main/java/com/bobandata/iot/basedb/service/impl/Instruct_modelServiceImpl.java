package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.bean.Instruct_model;
import com.bobandata.iot.basedb.bean.SimpleTree;
import com.bobandata.iot.basedb.repository.Instruct_modelRepository;
import com.bobandata.iot.basedb.repository.Instruct_model_setRepository;
import com.bobandata.iot.basedb.service.Instruct_modelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:09 2018/7/17.
 */

@Service
@Transactional
public class Instruct_modelServiceImpl extends BaseServiceImpl<Instruct_model, Integer> implements Instruct_modelService {

    @Autowired
    private Instruct_modelRepository instruct_modelRepository;

    @Override
    public Page<Instruct_model> selectPageList(Pageable pageable) {
        return instruct_modelRepository.selectPageList(pageable);
    }

    @Override
    public SimpleTree instructTree() {
        List<Instruct_model> instruct_models=instruct_modelRepository.findAll();
        SimpleTree treeHead =new SimpleTree(0,"指令模板",false);
        List<SimpleTree> childrenHead = new ArrayList<>();
        for(Instruct_model instruct_model:instruct_models){
            SimpleTree tree = new SimpleTree(instruct_model.getModelId(),instruct_model.getModelName(),false);
            childrenHead.add(tree);
        }
        treeHead.setChildren(childrenHead);
        return treeHead;
    }

    @Override
    public List<Instruct_model> findByModelName(String modelName) {
        return instruct_modelRepository.findByModelName(modelName);
    }

    @Override
    public List<Instruct_model> findSimilar(String modelName) {
        List<Instruct_model> all = instruct_modelRepository.findAll();

        List<Instruct_model> similar = new ArrayList<>();
        List<Instruct_model> identical = new ArrayList<>();
        List<Instruct_model> start = new ArrayList<>();
        List<Instruct_model> indexOf = new ArrayList<>();

        for(Instruct_model instruct_model : all){
            if(instruct_model.getModelName().equals(modelName)){
                identical.add(instruct_model);
            }
            else if(instruct_model.getModelName().startsWith(modelName)){
                start.add(instruct_model);
            }
            else if(instruct_model.getModelName().indexOf(modelName)!=-1){
                indexOf.add(instruct_model);
            }
        }
        similar.addAll(identical);
        similar.addAll(start);
        similar.addAll(indexOf);
        return similar;
    }
}
