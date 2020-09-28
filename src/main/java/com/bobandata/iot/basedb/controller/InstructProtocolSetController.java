package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.basedb.service.InstructProtocolSetService;
import com.bobandata.iot.entity.dms.Instruct;
import com.bobandata.iot.entity.dms.InstructProtocolSet;
import com.bobandata.iot.util.Constant;
import com.bobandata.iot.util.Result;
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
 * @Date: Created in 11:10 2018/7/20.
 */

@RestController
@RequestMapping("/instructProtocolSet")
public class InstructProtocolSetController {

    private static final Logger logger = LoggerFactory.getLogger(ProtocolController.class);

    @Autowired
    private InstructProtocolSetService ipsService;

    @RequestMapping("/selectPageList")
    public Result selectPageList(int page, int size){
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<InstructProtocolSet> ipss = ipsService.selectPageList(pageable);
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), ipss);
    }

    @RequestMapping(value = "/findByProtocolId")
    public Result findByProtocolId(Integer protocolId){
        try{
            List<InstructProtocolSet> ipss=ipsService.findByProtocolId(protocolId);
            if(ipss.size()>0){
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), ipss);
            }
            //状态为成功，结果为false
            else return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), false);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
    @RequestMapping("/deleteByProtocolIdAndInstructId")
    public Result deleteByProtocolIdAndInstructId(Integer protocolId,Integer instructId){
        try {
            ipsService.deleteByProtocolIdAndInstructId(protocolId,instructId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    @RequestMapping(value = "/deleteByProtocolId")
    @ResponseBody
    public Result de(Integer protocolId){
        try{
            ipsService.deleteByProtocolId(protocolId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //选择规约对应的指令
    @RequestMapping(value = "/protocolInstruct")
    @ResponseBody
    public Result protocolInstruct(Integer protocolId){
        try{
            List<Instruct> instructs = ipsService.protocolInstruct(protocolId);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), instructs);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //添加规约指令表
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result  addInstructProtocolSetData(Integer protocolId, Integer[] instructIds){
        try {
            List<InstructProtocolSet> ips =ipsService.addInstructProtocolSetData(protocolId,instructIds);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
        }catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
}

