package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.entity.dms.Param_model;
import com.bobandata.iot.util.Constant;
import com.bobandata.iot.util.Result;
import com.bobandata.iot.basedb.service.Param_modelService;
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
 * @Author: zhanglingzhi
 * @Description:    模板表接口
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:36 2018/7/18.
 */
@RestController
@RequestMapping("/paramModel")
public class Param_modelController {

    private static final Logger logger = LoggerFactory.getLogger(Param_model.class);

    @Autowired
    private Param_modelService param_modelService;

    //主键查询信息
    @RequestMapping("/findOne")
    public Result findOne(Integer id) {
        return deleteOrFind(id, Constant.MethodType.FIND.getMethodType());
    }

    //查询所有 模板
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Param_model> param_models = param_modelService.findAll();
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), param_models);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //保存记录：不带ID是新增，带ID是更新
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result addAcquired(Param_model param_model) {
        try {
            Param_model p = param_modelService.save(param_model);
            if (p != null) {
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), false);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //分页模板
    @RequestMapping("/selectPageList")
    public Result selectPageList(int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "modelId");
        Page<Param_model> param_models = param_modelService.selectPageList(pageable);
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), param_models);
    }

    //模糊查询
    @RequestMapping("/findSimilar")
    public Result findSimilar(String modelName){
        try{
            List<Param_model> paramModels= param_modelService.findSimilar(modelName);
            return  new Result(Constant.MethodResult.SUCCESS.getMethodResult(), paramModels);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
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
                param_modelService.delete(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                Param_model param_model = param_modelService.findOne(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), param_model);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
}