package com.bobandata.iot.basedb.common;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2018/12/3 10:04
 * @Description:
 */

public class UserInformation {
    private static Integer userId;

    public static Integer getUserId() {
        return userId;
    }

    public static void setUserId(Integer userId) {
        UserInformation.userId = userId;
    }
}