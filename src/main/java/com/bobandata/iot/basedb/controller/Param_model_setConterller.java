package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.basedb.service.Param_model_setService;
import com.bobandata.iot.entity.dms.Param_model;
import com.bobandata.iot.util.Constant;
import com.bobandata.iot.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:    参数 和 模板的中间表的接口
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:14 2018/7/19.
 */
@RestController
@RequestMapping("/paramModelSet")
public class Param_model_setConterller {

    private static final Logger logger = LoggerFactory.getLogger(Param_model.class);

    @Autowired
    private Param_model_setService param_model_setService;

    /**
     * 模版配置管理
     *  把模板ID和每个参数的ID 存入Param_model_set对象，然后批量存入Param_model_set表
     *  一个模板ID 对应多个参数ID
     * @param modelId   模板ID
     * @param paramIds  参数ID的集合
     * @return
     */
    @RequestMapping(value = "/saveRelation")
    @ResponseBody
    public Result saveRelationIf(Integer modelId, Integer [] paramIds) {
        try {
            param_model_setService.deleteParamIdByModelId(modelId);
            boolean b = param_model_setService.saveRelation(modelId, paramIds);
            if (b) {
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), false);
            }
        } catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @RequestMapping("findParamIdByModelId")
    public Result findParamIdByModelId(Integer modelId){
        try{
            List<Integer> paramIds = param_model_setService.findParamIdByModelId(modelId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), paramIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
    @RequestMapping("deleteParamIdByModelId")
    public Result deleteParamIdByModelId(Integer modelId){
        try{
            param_model_setService.deleteParamIdByModelId(modelId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

}
