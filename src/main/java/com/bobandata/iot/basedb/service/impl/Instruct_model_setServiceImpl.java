package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.bean.Instruct;
import com.bobandata.iot.basedb.bean.Instruct_model_set;
import com.bobandata.iot.basedb.bean.Protocol;
import com.bobandata.iot.basedb.repository.InstructRepository;
import com.bobandata.iot.basedb.repository.Instruct_model_setRepository;
import com.bobandata.iot.basedb.service.InstructService;
import com.bobandata.iot.basedb.service.Instruct_model_setService;
import com.bobandata.iot.basedb.service.Instruct_protocol_setService;
import com.bobandata.iot.basedb.service.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:59 2018/7/17.
 */
@Transactional
@Service
public class Instruct_model_setServiceImpl extends BaseServiceImpl<Instruct_model_set, Integer> implements Instruct_model_setService {

    @Autowired
    private Instruct_model_setRepository imsRepository;

    @Autowired
    private Instruct_protocol_setService ipsService;

    @Autowired
    private ProtocolService protocolService;

    @Override
    public Page<Instruct_model_set> selectPageList(Pageable pageable) {
        return imsRepository.selectPageList(pageable);
    }

    @Override
    public List<Integer> findInstructIdByModelId(Integer modelId) {
        return imsRepository.findInstructIdByModelId(modelId);
    }

    @Autowired
    private Instruct_model_setService imsService;
    @Autowired
    private InstructService instructService;
    @Override
    public List<Instruct> modelInstruct(Integer modelId) {
        List<Instruct> instructs = new ArrayList<>();
        List<Integer> instructIds =imsService.findInstructIdByModelId(modelId);
        for(Integer instructId:instructIds){
            Instruct instruct =instructService.findOne(instructId);
            instructs.add(instruct);
        }
        return instructs;
    }


    @Override
    public List<Instruct_model_set> addInstructModelSetData(Integer modelId,Integer [] instructIds) {
       deleteByModelId(modelId);
        List<Integer> list =new ArrayList<>();
        Collections.addAll(list,instructIds);
        List<Instruct_model_set> instruct_model_sets = new ArrayList<>();
        for(int instructId:list){
            Instruct_model_set instruct_model_set = new Instruct_model_set();
            instruct_model_set.setModelId(modelId);
            instruct_model_set.setInstructId(instructId);
            instruct_model_sets.add(instruct_model_set);
        }
        List<Instruct_model_set> ims=imsRepository.save(instruct_model_sets);
        return ims;
    }

    @Override
    public void deleteByModelIdAndInstructId(Integer modelId,Integer instructId) {
        List<Protocol> protocols = protocolService.findByModelId(modelId);
        for(Protocol protocol : protocols){
            ipsService.deleteByProtocolIdAndInstructId(protocol.getProtocolId(),instructId);
        }
        imsRepository.deleteByModelIdAndInstructId(modelId,instructId);
    }

    @Override
    public void deleteByModelId(Integer modelId) {
        List<Protocol> protocols = protocolService.findByModelId(modelId);
        for(Protocol protocol : protocols){
            ipsService.deleteByProtocolId(protocol.getProtocolId());
        }
        imsRepository.deleteByModelId(modelId);
    }

    @Override
    public void deleteByInstructId(Integer instructId) {
        imsRepository.deleteByInstructId(instructId);
    }

}

