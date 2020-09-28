package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.util.Constant;
import com.bobandata.iot.util.Result;
import com.bobandata.iot.basedb.service.MeterService;
import com.bobandata.iot.basedb.service.PulseService;
import com.bobandata.iot.entity.dms.Meter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:    电表对外接口
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:31 2018/7/17.
 */
@RestController
@RequestMapping("/meter")
public class MeterController {

    private static final Logger logger = LoggerFactory.getLogger(Meter.class);

    @Autowired
    private MeterService meterService;

    //主键查询信息
    @RequestMapping("/findOne")
    public Result findOne(Integer id) {
        return deleteOrFind(id, Constant.MethodType.FIND.getMethodType());
    }

    //查询所有
    @RequestMapping("/findAll")
    public Result findAll() {
        try{
            List<Meter> meters = meterService.findAll();
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), meters);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //根据电表ID查询电表
    @RequestMapping("/findMeterByErtuId")
    public Result findMeterByErtuId(Integer ertuId){
        try {
            List<Meter> meters = meterService.findMeterByErtuId(ertuId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), meters);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @Autowired
    private  PulseService pulseService;
    //保存记录：不带ID是新增，带ID是更新
    @RequestMapping(value = "/save")
    @ResponseBody
    @Transactional
    public Result addAcquired(Meter meter) {
        try {
            Meter p = meterService.save(meter);

            if (p != null) {
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), false);
            }
        } catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //分页接口
    @RequestMapping("/selectPageList")
    public Result selectPageList(int page, int size){
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "meterId");
        Page<Meter> meters = meterService.selectPageList(pageable);
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), meters);
    }

    //主键删除记录
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result deleterAcquired(Integer id) {
        return deleteOrFind(id, Constant.MethodType.DEL.getMethodType());
    }

    /**
     * 根据ID执行删除或者查找操作
     *
     * @param id         主键
     * @param methodType 方法类型
     * @return
     */

    @Transactional
    public Result deleteOrFind(Integer id, String methodType) {
        try {
            if (methodType.equals(Constant.MethodType.DEL.getMethodType())) {
                pulseService.deleteByMeterId(id);
                meterService.delete(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                Meter meter = meterService.findOne(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), meter);
            }
        } catch (Exception e) {
            logger.error(e.getMessage().toString());
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @RequestMapping("/findSimilar")
    public Result findSimilar(String meterName,Integer ertuId) {
        try {
            List<Meter> meters = meterService.findSimilar(meterName,ertuId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), meters);
        } catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
}