package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.BaseApplication;
import com.bobandata.iot.basedb.repository.Instruct_model_setRepository;
import com.bobandata.iot.basedb.service.Instruct_model_setService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:13 2018/8/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class Instruct_model_setServiceImplTest {
    @Autowired
    Instruct_model_setService ims;

    @Test
    public void delete(){
        ims.delete(3,1);
    }

}