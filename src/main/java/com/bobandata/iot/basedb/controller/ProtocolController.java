package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.basedb.bean.*;
import com.bobandata.iot.basedb.common.Constant;
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
import java.util.Map;

/**
 * @Author: liutuo
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 9:36 2018/7/17.
 */
@RestController
@RequestMapping("/protocol")
public class ProtocolController {

    private static final Logger logger = LoggerFactory.getLogger(ProtocolController.class);

    @Autowired
    private ProtocolService protocolService;


    @RequestMapping("/selectPageList")
    public Result selectPageList(int page, int size){
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "protocolId");
        Page<Protocol> protocols = protocolService.selectPageList(pageable);
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), protocols);
    }

    //主键查询信息
    @RequestMapping("/findOne")
    public Result findOne(Integer id) {
        return deleteOrFind(id, Constant.MethodType.FIND.getMethodType());
    }
    @RequestMapping("/findByProtocolName")
    public Result findOneByProtocolName(String protocolName){
        try{
            List<Protocol> protocols = protocolService.findByProtocolName(protocolName);
            return  new Result(Constant.MethodResult.SUCCESS.getMethodResult(), protocols);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
    @RequestMapping("/getList")
    public Result getList() {
        try{
            List<Protocol> protocols = protocolService.findAll();
            return  new Result(Constant.MethodResult.SUCCESS.getMethodResult(), protocols);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
    //保存记录：不带ID是新增，带ID是更新
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result addProtocol(Protocol protocol) {
        try {
            Protocol p = protocolService.save(protocol);
            if (p != null) {
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
    public Result deleterProtocol(Integer id) {
        return deleteOrFind(id, Constant.MethodType.DEL.getMethodType());
    }

    /**
     * 根据ID执行删除或者查找操作
     * @param id 主键
     * @param methodType 方法类型
     * @return
     */
    public Result deleteOrFind(Integer id, String methodType){
        try {
            if(methodType.equals(Constant.MethodType.DEL.getMethodType())){
                protocolService.delete(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            }else{
                Protocol protocol = protocolService.findOne(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), protocol);
            }
        } catch (Exception e) {
            logger.error(e.getMessage().toString());
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @RequestMapping("/protocolSort")
    public Result protocolSort(){
        try {
            Map map =protocolService.protocolSort();
            return  new Result(Constant.MethodResult.SUCCESS.getMethodResult(), map);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
    @RequestMapping("/modelProtocolTree")
    public Result modelProtocolTree(){
        try {
            SimpleTree tree=protocolService.modelProtocolTree();
            return  new Result(Constant.MethodResult.SUCCESS.getMethodResult(), tree);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
    @RequestMapping("/protocolTree")
    public Result protocolTree(){
        try {
            SimpleTree tree=protocolService.protocolTree();
            return  new Result(Constant.MethodResult.SUCCESS.getMethodResult(), tree);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @RequestMapping("/findSimilar")
    public Result findSimilar(String protocolName){
        try{
            List<Protocol> protocols = protocolService.findSimilar(protocolName);
            return  new Result(Constant.MethodResult.SUCCESS.getMethodResult(), protocols);
        }catch (Exception e){
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
}
