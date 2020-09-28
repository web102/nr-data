package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.basedb.service.AcquiredService;
import com.bobandata.iot.entity.dms.Acquired;
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
 * @Date: Created in 15:39 2018/7/17.
 */
@RestController
@RequestMapping("/acquired")
public class AcquiredController {

    private static final Logger logger = LoggerFactory.getLogger(AcquiredController.class);

    @Autowired
    private AcquiredService acquiredService;

    @RequestMapping("/selectPageList")
    public Result selectPageList(int page, int size){
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "acquiredId");
        Page<Acquired> acquired= acquiredService.selectPageList(pageable);
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), acquired);
    }

    //主键查询信息
    @RequestMapping("/findOne")
    public Result findOne(Integer id) {
        return deleteOrFind(id, Constant.MethodType.FIND.getMethodType());
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        try{
            List<Acquired> acquireds=acquiredService.findAll();
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), acquireds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);

        }
    }

    //保存记录：不带ID是新增，带ID是更新
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result addAcquired(Acquired acquired) {
        try {
            Acquired a = acquiredService.save(acquired);
            if(a!=null){
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            }else {
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), false);
            }
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
     * @param id 主键
     * @param methodType 方法类型
     * @return
     */
    private Result deleteOrFind(Integer id, String methodType) {
        try {
            if(methodType.equals(Constant.MethodType.DEL.getMethodType())) {
                acquiredService.delete(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            }else {
                Acquired acquired = acquiredService.findOne(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(),acquired);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage().toString());
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

}
