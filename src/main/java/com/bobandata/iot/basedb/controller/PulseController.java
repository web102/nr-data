package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.entity.dms.Pulse;
import com.bobandata.iot.basedb.service.PulseService;
import com.bobandata.iot.util.Constant;
import com.bobandata.iot.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:    信息体表接口
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:32 2018/7/17.
 */
@RestController
@RequestMapping("/pulse")
public class PulseController {

    private static final Logger logger = LoggerFactory.getLogger(Pulse.class);

    @Autowired
    private PulseService pulseService;

    //主键查询信息
    @RequestMapping("/findOne")
    public Result findOne(Integer id) {
        return deleteOrFind(id, Constant.MethodType.FIND.getMethodType());
    }

    @RequestMapping("/findByMeterId")
    public Result findByMeterId(Integer meterId) {
        try{
            List<Pulse> pulses = pulseService.findByMeterId(meterId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), pulses);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //批量保存
    @RequestMapping(value = "/adds")
    @ResponseBody
    public Result adds(List<Pulse> pulse) {
        try {
            List<Pulse> sp = pulseService.save(pulse);
            if (sp.size() > 0) {
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), false);
            }
        } catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //保存记录：不带ID是新增，带ID是更新
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result add(Pulse pulse) {
        try {
            Pulse p = pulseService.save(pulse);
            if (p != null) {
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), false);
            }
        } catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
//    @RequestMapping(value = "/addsByMeterIdAndParamIds")
//    @ResponseBody
//    public Result addsByMeterIdAndParamIds(List<Integer> paramIds) {
//        pulseService.deleteByMeterId(paramIds.get(0));
//        pulseService.addsByMeterIdAndParamIds(paramIds);
//        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
//    }
    @RequestMapping(value = "/addsByMeterIdAndModelIds")
    @ResponseBody
    public Result addsByMeterIdAndModelIds(Integer[] modelIds) {
        Integer meterId = modelIds[0];
        pulseService.deleteByMeterId(meterId);
        for(Integer i=1;i<modelIds.length;i++){
            pulseService.toLeadPulse(meterId,modelIds[i]);
        }
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
    }

    //分页
    @RequestMapping("/selectPageList")
    public Result selectPageList(int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "pulseId");
        Page<Pulse> pulses = pulseService.selectPageList(pageable);
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), pulses);
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
    public Result deleteOrFind(Integer id, String methodType) {
        try {
            if (methodType.equals(Constant.MethodType.DEL.getMethodType())) {
                pulseService.delete(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                Pulse pulse = pulseService.findOne(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), pulse);
            }
        } catch (Exception e) {
            logger.error(e.getMessage().toString());
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
    @RequestMapping("/findSimilar")
    public Result findSimilar(String pulseName,Integer meterId) {
        try {
            List<Pulse> pulses = pulseService.findSimilar(pulseName,meterId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), pulses);
        } catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    /**
     * 根据paramModelId查询参数模板对应的参数对象params集合,
     * 然后遍历params集合跟meterid一起存入信息体pulse对象
     * 然后集合list 信息体对象批量插入信息体表
     * @param meterId
     * @param paramModelId
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result add(Integer meterId , Integer paramModelId) {
        try {
            boolean b = pulseService.toLeadPulse(meterId, paramModelId);
            if (b) {
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), false);
            }
        } catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

}