package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.basedb.bean.*;
import com.bobandata.iot.basedb.common.Constant;
import com.bobandata.iot.basedb.repository.Instruct_protocol_setRepository;
import com.bobandata.iot.basedb.service.InstructService;
import com.bobandata.iot.basedb.service.Instruct_model_setService;
import com.bobandata.iot.basedb.service.ProtocolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 10:32 2018/7/20.
 */

@RestController
@RequestMapping("/instructModelSet")
public class Instruct_model_setController {
    private static final Logger logger = LoggerFactory.getLogger(Instruct_modelController.class);

    @Autowired
    private Instruct_model_setService instruct_model_setService;

    @Autowired
    private Instruct_protocol_setRepository ips;

    @Autowired
    private ProtocolService protocolService;



    @RequestMapping("/selectPageList")
    public Result selectPageList(int page, int size){
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<Instruct_model_set> instruct_models = instruct_model_setService.selectPageList(pageable);
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instruct_models);
    }

    @RequestMapping("/delete")
    public Result deleteByModelIdAndInstructId(Integer modelId,Integer instructId){
        try{
            instruct_model_setService.deleteByModelIdAndInstructId(modelId,instructId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @RequestMapping("/findInstructIdByModelId")
    public Result findInstructIdByModelId(Integer modelId){
        try{
            List<Integer> instructIds = instruct_model_setService.findInstructIdByModelId(modelId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instructIds);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @Autowired
    InstructService instructService;
    //规约 模板中的指令
    @RequestMapping(value = "/instructSortByModelId")
    @ResponseBody
    public Result instructSortByModelId(Integer modelId){
        try{
            List<Instruct> instructs = instruct_model_setService.modelInstruct(modelId);
            Map map = instructService.instructSortByType(instructs);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), map);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @RequestMapping(value = "/findInstructByModelId")
    @ResponseBody
    public Result findInstructByModelId(Integer modelId){
        try{
            List<Instruct> instructs = instruct_model_setService.modelInstruct(modelId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instructs);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
    //添加模板指令表
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result addInstructModelSetData(Integer modelId,Integer[] instructIds) {
        try{
            instruct_model_setService.addInstructModelSetData(modelId, instructIds);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(),Constant.ResultType.B00.getResultType(),false);
        }
    }
    @RequestMapping(value = "/getList")
    public Result addInstruc(Integer modelId,Integer[] instructIds) {
        try{

            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instruct_model_setService.findAll());
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(),Constant.ResultType.B00.getResultType(),false);
        }
    }
}
