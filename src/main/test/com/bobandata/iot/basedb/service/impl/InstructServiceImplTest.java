package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.BaseApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:03 2018/8/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class InstructServiceImplTest {


    @Autowired
    InstructServiceImpl instructService;
    @Test
    public void findSimilar() {
        instructService.findSimilar("功率",1,0);

    }
}