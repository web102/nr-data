package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.basedb.bean.Instruct_model;
import com.bobandata.iot.basedb.bean.SimpleTree;
import com.bobandata.iot.basedb.bean.Result;
import com.bobandata.iot.basedb.common.Constant;
import com.bobandata.iot.basedb.service.Instruct_modelService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 14:47 2018/7/17.
 */

@RestController
@RequestMapping("/instructModel")
public class Instruct_modelController {

    private static final Logger logger = LoggerFactory.getLogger(Instruct_modelController.class);

    @Autowired
    private Instruct_modelService instruct_modelService;

    @Autowired
    private Instruct_model_setService imsService;

    @Autowired
    private ProtocolService protocolService;


    @RequestMapping("/selectPageList")
    public Result selectPageList(int page, int size){
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "modelId");
        Page<Instruct_model> instruct_models = instruct_modelService.selectPageList(pageable);
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instruct_models);
    }

    //主键查询信息
    @RequestMapping("/findOne")
    public Result findOne(Integer id) {
        return deleteOrFind(id, Constant.MethodType.FIND.getMethodType());
    }

    @RequestMapping("/findByModelName")
    public Result findByModelName(String modelName) {
        try {
            List<Instruct_model> instruct_models = instruct_modelService.findByModelName(modelName);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instruct_models);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        try{
            List<Instruct_model> instructModels=instruct_modelService.findAll();
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instructModels);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //保存记录：不带ID是新增，带ID是更新
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result addInstructModel(Instruct_model instruct_model) {
        try {
            Instruct_model i = instruct_modelService.save(instruct_model);
            if (i != null) {
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), false);
            }
        } catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //主键删除记录
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result deleterInstruct_model(Integer id) {
        imsService.deleteByModelId(id);
        return deleteOrFind(id, Constant.MethodType.DEL.getMethodType());
    }

    /**
     * 根据ID执行删除或者查找操作
     * @param id 主键
     * @param methodType 方法类型
     * @return
     */
    private Result deleteOrFind(Integer id, String methodType) {
        try{
            if(methodType.equals(Constant.MethodType.DEL.getMethodType())) {
                instruct_modelService.delete(id);

                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            }else {
                Instruct_model instruct_model = instruct_modelService.findOne(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instruct_model);
            }
        }catch (Exception e){
            logger.error(e.getMessage().toString());
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(),Constant.ResultType.B00.getResultType(),false);
        }
    }
    @RequestMapping("instructTree")
    public Result instructTree(){
        try {
            SimpleTree tree = instruct_modelService.instructTree();
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), tree);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
    @RequestMapping("/findSimilar")
    public Result findSimilar(String modelName) {
        try {
            List<Instruct_model> instruct_models = instruct_modelService.findSimilar(modelName);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instruct_models);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
}
