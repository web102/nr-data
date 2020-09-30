package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.entity.dms.User;
import com.bobandata.iot.basedb.repository.UserRepository;
import com.bobandata.iot.basedb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 11:50 2018/7/17.
 */
@Service
public class UserServicelmpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<User> selectPageList(Pageable pageable) {
        return userRepository.selectPageList(pageable);
    }

    @Override
    public User findUser(String username, String password) {
        List<User> users = userRepository.findUserByNameAndPassword(username, password);
        if (users.size()>0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public User update(String username, String password, String newPassword) {
        List<User> users = userRepository.findUserByNameAndPassword(username, password);
        if(users.size()>0){
            User user = users.get(0);
            user.setUserName(username);
            user.setPassword(newPassword);
            userRepository.save(users);
            return user;
        }
        return null;
    }

    @Override
    public List<User> findByName(String username) {
        return userRepository.findUserByName(username);
    }
}