package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.BaseApplication;
import com.bobandata.iot.basedb.bean.Instruct_model_set;
import com.bobandata.iot.basedb.bean.Instruct_protocol_set;
import com.bobandata.iot.basedb.service.Instruct_model_setService;
import com.bobandata.iot.basedb.service.Instruct_protocol_setService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 18:45 2018/7/19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class Instruct_modelServiceImplTest {

    @Autowired
    Instruct_model_setService imsService;
    @Test
    public void addInstructModelSetData() {
        Integer modelId =9;
//        List<Integer> instructIds = new ArrayList<>();
//        instructIds.add(1);
//        instructIds.add(2);
//        instructIds.add(3);
        Integer instructIds[] =new Integer[]{1,2,3};
        List<Instruct_model_set> list=imsService.addInstructModelSetData(modelId,instructIds);
        System.out.println(list.toString());
    }

    @Autowired
    Instruct_protocol_setService ipsService;
    @Test
    public void addInstructProtocolSetData(){
        Integer protocolModelId=1;
//        List<Integer> instructIds = new ArrayList<>();
//        instructIds.add(1);
//        instructIds.add(3);
        Integer instructIds[] =new Integer[]{1,2,3};
        List<Instruct_protocol_set> lsit =ipsService.addInstructProtocolSetData(protocolModelId,instructIds);
        System.out.println(lsit);
    }
}