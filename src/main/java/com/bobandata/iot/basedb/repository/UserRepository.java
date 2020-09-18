package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.basedb.bean.Protocol;
import com.bobandata.iot.basedb.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 11:51 2018/7/17.
 */
@Repository
public interface UserRepository extends BaseRepository<User, Integer> {

    @Query("SELECT u FROM User u")
    Page<User> selectPageList(Pageable pageable);

    //根据用户名查询用户
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    List<User> findUserByName(String username);

    //查询姓名为name，密码为password的用户，判断用户存不存在，
    // 存在返回true\不存在返回false
    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    List<User> findUserByNameAndPassword(String username, String password);

    @Query("SELECT u FROM User u WHERE u.role = 'root'")
    List<User> findRoot();



}