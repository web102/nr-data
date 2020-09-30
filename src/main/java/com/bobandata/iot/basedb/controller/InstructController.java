package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.basedb.service.InstructProtocolSetService;
import com.bobandata.iot.entity.dms.Instruct;
import com.bobandata.iot.util.Constant;
import com.bobandata.iot.util.Result;
import com.bobandata.iot.basedb.service.InstructService;
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
import java.util.Map;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 10:50 2018/7/17.
 */

@RestController
@RequestMapping("/instruct")
public class InstructController {

    private static final Logger logger = LoggerFactory.getLogger(InstructController.class);

    @Autowired
    private InstructService instructService;

    @Autowired
    private InstructProtocolSetService ipsService;

    @RequestMapping("/selectPageList")
    public Result selectPageList(int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "instructId");
        Page<Instruct> instructs = instructService.selectPageList(pageable);
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instructs);
    }

    //主键查询信息
    @RequestMapping("/findOne")
    public Result findOne(Integer id) {
        try {
            Instruct instruct = instructService.findOne(id);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instruct);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
    //
    @RequestMapping(value = "/findByInstructName")
    @ResponseBody
    public Result findByInstructName(String instructName) {
        try {
            List<Instruct> instructs = instructService.findByInstructName(instructName);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instructs);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        try{
            List<Instruct> instructs=instructService.findAll(new Sort("instructId"));
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(),instructs);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);

        }
    }

    //保存记录：不带ID是新增，带ID是更新
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result addInstruct(Instruct instruct) {
        try {
            Instruct i = instructService.save(instruct);
            if (i != null) {
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), false);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //主键删除记录
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result delete(Integer id) {
        return deleteOrFind(id, Constant.MethodType.DEL.getMethodType());
    }



    //分类显示所有Instruct及选择
    @RequestMapping(value = "/instructSortByType")
    @ResponseBody
    public Result instructSortByType() {
        try {
            Map map = instructService.instructSortByType(instructService.findAll());
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), map);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @RequestMapping(value = "/findSimilar")
    @ResponseBody
    public Result findSimilar(String instructName,Integer protocolId) {
        try {
            List<Instruct> instructs = instructService.findSimilar(instructName,protocolId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instructs);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
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
                ipsService.deleteByInstructId(id);
                instructService.delete(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                Instruct instruct = instructService.findOne(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instruct);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
}
