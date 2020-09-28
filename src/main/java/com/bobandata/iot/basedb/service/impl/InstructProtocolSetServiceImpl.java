package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.repository.InstructProtocolSetRepository;
import com.bobandata.iot.basedb.service.InstructProtocolSetService;
import com.bobandata.iot.basedb.service.InstructService;
import com.bobandata.iot.entity.dms.Instruct;
import com.bobandata.iot.entity.dms.InstructProtocolSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
public class InstructProtocolSetServiceImpl extends BaseServiceImpl<InstructProtocolSet, Integer> implements InstructProtocolSetService {
    @Autowired
    private InstructProtocolSetRepository ipsRepository;

    @Override
    public Page<InstructProtocolSet> selectPageList(Pageable pageable) {
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
    private InstructProtocolSetService ipsService;

    @Override
    public List<InstructProtocolSet> addInstructProtocolSetData(Integer protocolId,Integer[] instructIds) {
        ipsRepository.deleteByProtocolId(protocolId);
        List<Integer> ids = new ArrayList<>(Arrays.asList(instructIds));
        List<InstructProtocolSet> ipss =new ArrayList<>();
        for(Integer instructId:ids){
            InstructProtocolSet ips = new InstructProtocolSet();
            ips.setInstructId(instructId);
            ips.setProtocolId(protocolId);
            ipss.add(ips);
        }
        ipsService.save(ipss);
        return ipss;
    }

    @Override
    public List<InstructProtocolSet> findByProtocolId(Integer protocolId) {
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
