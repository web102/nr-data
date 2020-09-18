package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.bean.Instruct;
import com.bobandata.iot.basedb.bean.Instruct_protocol_set;
import com.bobandata.iot.basedb.repository.Instruct_protocol_setRepository;
import com.bobandata.iot.basedb.service.InstructService;
import com.bobandata.iot.basedb.service.Instruct_model_setService;
import com.bobandata.iot.basedb.service.Instruct_protocol_setService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:48 2018/7/17.
 */
@Transactional
@Service
public class Instruct_protocol_setServiceImpl extends BaseServiceImpl<Instruct_protocol_set, Integer> implements Instruct_protocol_setService {
    @Autowired
    private Instruct_protocol_setRepository ipsRepository;

    @Override
    public Page<Instruct_protocol_set> selectPageList(Pageable pageable) {
        return ipsRepository.selectPageList(pageable);
    }

    @Autowired
    private InstructService instructService;
    @Override
    public List<Instruct> protocolInstruct(Integer protocolId) {
        List<Instruct> instructs = new ArrayList<>();
        List<Integer> instructIds =ipsRepository.findInstructIdByProtocolId(protocolId);
        for(Integer instructId:instructIds){
            Instruct instruct =instructService.findOne(instructId);
            instructs.add(instruct);
        }
        return instructs;
    }

    @Autowired
    private Instruct_protocol_setService ipsService;

    @Override
    public List<Instruct_protocol_set> addInstructProtocolSetData(Integer protocolId,Integer[] instructIds) {
        ipsRepository.deleteByProtocolId(protocolId);
        List<Integer> ids = new ArrayList<>(Arrays.asList(instructIds));
        List<Instruct_protocol_set> ipss =new ArrayList<>();
        for(Integer instructId:ids){
            Instruct_protocol_set ips = new Instruct_protocol_set();
            ips.setInstructId(instructId);
            ips.setProtocolId(protocolId);
            ipss.add(ips);
        }
        ipsService.save(ipss);
        return ipss;
    }

    @Override
    public List<Instruct_protocol_set> findByProtocolId(Integer protocolId) {
        return ipsRepository.findByProtocolId(protocolId);
    }

    @Override
    public void deleteByProtocolIdAndInstructId(Integer protocolId, Integer instructId) {
        ipsRepository.deleteByProtocolIdAndInstructId(protocolId,instructId);
    }

    @Override
    public void deleteByProtocolId(Integer protocolId) {
        ipsRepository.deleteByProtocolId(protocolId);
    }

    @Override
    public void deleteByInstructId(Integer instructId) {
        ipsRepository.deleteByInstructId(instructId);
    }
}
