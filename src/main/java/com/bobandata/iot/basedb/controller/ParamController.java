package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.basedb.bean.Meter;
import com.bobandata.iot.basedb.bean.Param;
import com.bobandata.iot.basedb.bean.Result;
import com.bobandata.iot.basedb.common.Constant;
import com.bobandata.iot.basedb.service.MeterService;
import com.bobandata.iot.basedb.service.ParamService;
import com.bobandata.iot.basedb.service.Param_model_setService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhanglingzhi
 * @Description: 参数表接口
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:36 2018/7/18.
 */
@RestController
@RequestMapping("/param")
public class ParamController {

    private static final Logger logger = LoggerFactory.getLogger(Param.class);

    @Autowired
    private ParamService paramService;

    @Autowired
    private MeterService meterService;

    @Autowired
    private Param_model_setService pmsService;
    //主键查询信息
    @RequestMapping("/findOne")
    public Result findOne(Integer id) {
        return deleteOrFind(id, Constant.MethodType.FIND.getMethodType());
    }

    //查询所有参数
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Param> params = paramService.findAll();
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), params);
        } catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //保存记录：不带ID是新增，带ID是更新
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result addAcquired(Param param) {
        try {
            Param p = paramService.save(param);
            if (p != null) {
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), false);
            }
        } catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //分页参数
    @RequestMapping("/selectPageList")
    public Result selectPageList(int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "paramId");
        Page<Param> params = paramService.selectPageList(pageable);
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), params);
    }

    //分类接口
    @RequestMapping(value = "/selectSortList")
    @ResponseBody
    public Result selectSortList() {
        try {
            Map<Integer, List<Param>> integerListMap = paramService.paramSortByType();
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), integerListMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //模糊查询
    @RequestMapping("/findSimilar")
    public Result findSimilar(String paramName) {
        try {
            List<Param> params = paramService.findSimilar(paramName);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), params);
        } catch (Exception e) {
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
                paramService.delete(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                Param param = paramService.findOne(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), param);
            }
        } catch (Exception e) {
            logger.error(e.getMessage().toString());
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @RequestMapping("findByMeterId")
    public Result findParamIdByMeterId(Integer meterId){
        try{
            Meter meter = meterService.findOne(meterId);
            List<Integer> paramIds = pmsService.findParamIdByModelId(meter.getParamModelId());
            List<Param> params = new ArrayList<>();
            for(Integer paramId : paramIds){
                Param param = paramService.findOne(paramId);
                params.add(param);
            }
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), params);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
}