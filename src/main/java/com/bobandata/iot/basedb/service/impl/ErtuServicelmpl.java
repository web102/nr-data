package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.repository.ErtuRepository;
import com.bobandata.iot.basedb.repository.MeterRepository;
import com.bobandata.iot.basedb.service.ErtuService;
import com.bobandata.iot.entity.dms.Ertu;
import com.bobandata.iot.entity.dms.Meter;
import com.bobandata.iot.util.SimpleTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:37 2018/7/17.
 */
@Service
public class ErtuServicelmpl extends BaseServiceImpl<Ertu, Integer> implements ErtuService {

    @Autowired
    private ErtuRepository ertuRepository;

    @Autowired
    private MeterRepository meterRepository;


    @Override
    public Page<Ertu> selectPageList(Pageable pageable) {
        return ertuRepository.selectPageList(pageable);
    }

    @Override
    public List<Ertu> findByName(String ertuName) {
        return ertuRepository.findByErtuName(ertuName);
    }

    @Override
    public SimpleTree ertuTree() {
        SimpleTree tree = new SimpleTree(0,"tree");
        List<SimpleTree> children = new ArrayList<>();

        List<Ertu> all = ertuRepository.findAll(new Sort("ertuId"));
        for(Ertu ertu : all){
            SimpleTree ertus = new SimpleTree(ertu.getErtuId(),ertu.getErtuName());
            children.add(ertus);
        }
        tree.setChildren(children);
        return tree;
    }

    @Override
    public SimpleTree ertuMeterTree() {
        SimpleTree tree = new SimpleTree(0,"tree");
        List<SimpleTree> children = new ArrayList<>();

        List<Ertu> ertuAll = ertuRepository.findAll(new Sort("ertuId"));
        for(Ertu ertu : ertuAll){
            Integer ertuId = ertu.getErtuId();
            SimpleTree ertus = new SimpleTree(ertuId,ertu.getErtuName(),ertu.getProtocolId());
            List<SimpleTree> ertuChildren = new ArrayList<>();
            List<Meter> meters = meterRepository.findByErtuId(ertuId);
            for (Meter meter : meters) {
                SimpleTree metersT = new SimpleTree(meter.getMeterId(), meter.getMeterName(), meter.getParamModelId(), meter.getErtuId());
                ertuChildren.add(metersT);
            }
            ertus.setChildren(ertuChildren);
            children.add(ertus);
        }
        tree.setChildren(children);
        return tree;
    }

    @Override
    public List<Ertu> findSimilar(String ertuName) {
        List<Ertu> all = ertuRepository.findAll();

        List<Ertu> similar = new ArrayList<>();
        List<Ertu> identical = new ArrayList<>();
        List<Ertu> start = new ArrayList<>();
        List<Ertu> indexOf = new ArrayList<>();

        for(Ertu ertu : all){
            if(ertu.getErtuName().equals(ertuName)){
                identical.add(ertu);
            }
            else if(ertu.getErtuName().startsWith(ertuName)){
                start.add(ertu);
            }
            else if(ertu.getErtuName().indexOf(ertuName)!=-1){
                indexOf.add(ertu);
            }
        }
        similar.addAll(identical);
        similar.addAll(start);
        similar.addAll(indexOf);
        return similar;
    }


}