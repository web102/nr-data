package com.bobandata.iot.basedb.controller;

import com.bobandata.iot.util.Constant;
import com.bobandata.iot.util.Result;
import com.bobandata.iot.entity.dms.User;
import com.bobandata.iot.basedb.common.UserInformation;
import com.bobandata.iot.basedb.service.UserService;
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
 * @Description: 用户表接口
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 11:55 2018/7/17.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @Autowired
    private UserService userService;

    //主键查询信息
    @RequestMapping("/findOne")
    public Result findOne(Integer id) {
        return deleteOrFind(id, Constant.MethodType.FIND.getMethodType());
    }

    //保存记录：不带ID是新增，带ID是更新
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result addAcquired(User user) {
        try {
            List<User> root = userService.findRoot();
            String role = null;
            if(root.size()>0){
                role = root.get(0).getUserName();
            }else {
                role="root";
            }

            if(!user.getRole().equals(role)){
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), "秘钥错误！");
            }

            user.setRole("0");
            if (user.getUserId() == null) {
                List<User> users = userService.findByName(user.getUserName());
                if (users.size() > 0) {
                    return new Result(Constant.MethodResult.FAIL.getMethodResult(), "用户名已存在！");
                }
            }
            User p = userService.save(user);
            if (p != null) {
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                return new Result(Constant.MethodResult.FAIL.getMethodResult(), "保存失败！");
            }


        } catch (Exception e) {
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }

    //分页
    @RequestMapping("/selectPageList")
    public Result selectPageList(int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "userId");
        Page<User> users = userService.selectPageList(pageable);
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), users);
    }

    //登录
    @RequestMapping("/login")
    public Result login(String username, String password) {
        User user = userService.findUser(username, password);
        if (user!=null) {
            UserInformation.setUserId(user.getUserId());
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
        } else {
            return new Result(Constant.MethodResult.FAIL.getMethodResult(), false);
        }
    }

    //更新密码
    @RequestMapping("/update")
    public Result upDatePassword(String username, String password, String newPassword) {
        try {
            User user = userService.update(username, password, newPassword);
            if(user==null){
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), false);
            }
            userService.save(user);
            return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
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

    @RequestMapping(value = "/getList")
    @ResponseBody
    public Result getList() {
        List<User> users = userService.findAll();
        return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), users);
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
                userService.delete(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), true);
            } else {
                User user = userService.findOne(id);
                return new Result(Constant.MethodResult.SUCCESS.getMethodResult(), user);
            }
        } catch (Exception e) {
            logger.error(e.getMessage().toString());
            return new Result(Constant.ErrorCode.EXCEPTION.getErrorCode(), Constant.MethodResult.FAIL.getMethodResult(), Constant.ResultType.B00.getResultType(), false);
        }
    }
}
