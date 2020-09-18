package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.BaseApplication;
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
 * @Date: Created in 14:33 2018/8/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class Instruct_protocol_setServiceImplTest {

    @Autowired
    Instruct_protocol_setServiceImpl instruct_protocol_setService;
    @Test
    public void deleteByProtocolIdAndInstructId() {
        instruct_protocol_setService.deleteByProtocolIdAndInstructId(4,8);
    }
}