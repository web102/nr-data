package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.asynplt.connector.MasterProtocol;
import com.bobandata.iot.asynplt.util.DataTypeConst;
import com.bobandata.iot.basedb.BaseApplication;
import com.bobandata.iot.basedb.service.MeterService;
import com.bobandata.iot.basedb.service.Param_model_setService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:02 2018/7/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class Param_model_setServicelmplTest {

    @Autowired
    private Param_model_setService param_model_setService;

    @Autowired
    private MeterService meterService;

    @Test
    public void saveRelation() throws Exception {
//        Integer modelId = 1;
//        List<Integer> paramIds = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            paramIds.add(i + 1);
//        }
//        param_model_setService.saveRelation(modelId, paramIds);
        MasterProtocol.sendInstruct(1,4,new Date(),new Date(),DataTypeConst.factory);
    }

/*    @Test
    public void toLeadPulse() throws Exception {
        meterService.toLeadPulse(1, 1);

    }*/

}