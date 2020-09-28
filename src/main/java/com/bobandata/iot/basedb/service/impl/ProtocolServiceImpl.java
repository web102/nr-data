package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.repository.ProtocolRepository;
import com.bobandata.iot.basedb.service.ProtocolService;
import com.bobandata.iot.entity.dms.Protocol;
import com.bobandata.iot.util.SimpleTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: liutuo
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:44 2018/7/16.
 */
@Service
public class ProtocolServiceImpl extends BaseServiceImpl<Protocol, Integer> implements ProtocolService{

    @Autowired
    private ProtocolRepository protocolRepository;

    @Override
    public Page<Protocol> selectPageList(Pageable pageable) {
        return protocolRepository.selectPageList(pageable);
    }

    @Override
    public SimpleTree protocolTree() {
        SimpleTree treeHead =new SimpleTree(0,"规约",0,false);
        List<SimpleTree> childrenHead =new ArrayList<>();
        treeHead.setChildren(childrenHead);
        List<Protocol> protocols = this.findAll();
        for(Protocol protocol : protocols){
            SimpleTree tree =new SimpleTree(protocol.getProtocolId(),protocol.getProtocolName(),0,false);
            childrenHead.add(tree);
        }
        return treeHead;
    }

    @Override
    public List<Protocol> findByProtocolName(String protocolName) {
        return protocolRepository.findByProtocolName(protocolName);
    }

    @Override
    public List<Protocol> findSimilar(String protocolName) {
        List<Protocol> all = protocolRepository.findAll();

        List<Protocol> similar = new ArrayList<>();
        List<Protocol> identical = new ArrayList<>();
        List<Protocol> start = new ArrayList<>();
        List<Protocol> indexOf = new ArrayList<>();

        for(Protocol protocol : all){
            if(protocol.getProtocolName().equals(protocolName)){
                identical.add(protocol);
            }
            else if(protocol.getProtocolName().startsWith(protocolName)){
                start.add(protocol);
            }
            else if(protocol.getProtocolName().indexOf(protocolName)!=-1){
                indexOf.add(protocol);
            }
        }
        similar.addAll(identical);
        similar.addAll(start);
        similar.addAll(indexOf);
        return similar;
    }

    @Override
    public SimpleTree modelProtocolTree() {
        Map<String ,List<Protocol>> map =protocolSort();
        SimpleTree treeHead =new SimpleTree(0,"102规约",0,false);
//        List<SimpleTree> childrenHead =new ArrayList<>();
//        for(String title:map.keySet()){
//            SimpleTree tree =new SimpleTree(0,title,0,false);
//
//            List<SimpleTree> simpleTrees =new ArrayList<>();
//            List<Protocol> protocols= map.get(title);
//            for(Protocol protocol:protocols){
//                SimpleTree simpleTree =new SimpleTree(protocol.getProtocolId(),protocol.getProtocolName(),protocol.getProtocolModelId(),false);
//                simpleTrees.add(simpleTree);
//            }
//            tree.setChildren(simpleTrees);
//            childrenHead.add(tree);
//        }
//        treeHead.setChildren(childrenHead);
        return treeHead;
    }

    @Override
    public Map<String, List<Protocol>> protocolSort() {
        Map<String,List<Protocol>> map =new HashMap<>();
//        List<Instruct_model> instruct_models =instruct_modelRepository.findAll();
//        for(Instruct_model instruct_model:instruct_models){
//            map.put(instruct_model.getModelName(),new ArrayList<>());
//        }
//        map.put("未配置",new ArrayList<>());
//
//        List<Protocol> protocols =protocolRepository.findAll();
//        for(Protocol protocol:protocols){
//            Instruct_model instruct_model = instruct_modelRepository.findOne(protocol.getProtocolModelId());
//            if(instruct_model==null) {
//                map.get("未配置").add(protocol);
//                continue;
//            }
//            map.get(instruct_model.getModelName()).add(protocol);
//        }
//        if(map.get("未配置").size()==0){
//            map.remove("未配置");
//        }
        return map;
    }

}
