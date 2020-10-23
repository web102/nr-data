package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.repository.InstructRepository;
import com.bobandata.iot.basedb.service.InstructProtocolSetService;
import com.bobandata.iot.basedb.service.InstructService;
import com.bobandata.iot.entity.dms.Instruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 10:53 2018/7/17.
 */
@Service
public class InstructServiceImpl extends BaseServiceImpl<Instruct, Integer> implements InstructService {

    @Autowired
    private InstructRepository instructRepository;
    @Autowired
    private InstructProtocolSetService ipsService;

    @Override
    public Page<Instruct> selectPageList(Pageable pageable) {
        return instructRepository.selectPageList(pageable);
    }

    /**
     * 通过类型给指令分类
     * @return map<类型名，list<指令></>></>
     */
    @Override
    public Map<String,List<Instruct>> instructSortByType(List<Instruct> instructs){
        Map<String,List<Instruct>> map = new HashMap<>();

        List<Instruct> instructTypes = instructRepository.findAllInstructTypes();
        for(Instruct instructType:instructTypes) {
            map.computeIfAbsent(instructType.getInstructType(), k -> new ArrayList<>());
            map.get(instructType.getInstructType()).add(instructType);
        }

        for (Map.Entry<String,List<Instruct>> entry : map.entrySet()){
            entry.getValue().sort(new Comparator<Instruct>() {
                @Override
                public int compare(Instruct o1, Instruct o2) {
                    return o1.getInstructPath().compareTo(o2.getInstructPath());
                }
            });
        }
        return map;
    }

    /**
     *指令模糊查询
     * @param instructName  指令表中全部指令
     * @param protocolId 规约id=? 的全部指令
     * @return list<指令></>
     */
    @Override
    public List<Instruct> findSimilar(String instructName,Integer protocolId) {
        List<Instruct> all;
        if(protocolId==0){
            all = instructRepository.findAll();
        } else{
            all = ipsService.protocolInstruct(protocolId);
        }
        List<Instruct> similar = new ArrayList<>();
        List<Instruct> identical = new ArrayList<>();
        List<Instruct> start = new ArrayList<>();
        List<Instruct> indexOf = new ArrayList<>();
        for(Instruct instruct : all){
            if(instruct.getInstructName().equals(instructName)){
                identical.add(instruct);
            }
            else if(instruct.getInstructName().startsWith(instructName)){
                start.add(instruct);
            }
            else if(instruct.getInstructName().contains(instructName)){
                indexOf.add(instruct);
            }
        }
        similar.addAll(identical);
        similar.addAll(start);
        similar.addAll(indexOf);
        return similar;
    }

    @Override
    public List<Instruct> findByInstructIds(List<Integer> ids) {
        return instructRepository.findByInstructIds(ids);
    }

    @Override
    public List<Instruct> findByInstructName(String instructName) {
        return instructRepository.findByInstructName(instructName);
    }
}
