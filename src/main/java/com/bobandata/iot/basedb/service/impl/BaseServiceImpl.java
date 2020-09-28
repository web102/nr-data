package com.bobandata.iot.basedb.service.impl;

import com.bobandata.iot.basedb.repository.BaseRepository;
import com.bobandata.iot.basedb.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: liutuo
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 18:04 2018/7/16.
 */
public abstract class BaseServiceImpl<M, ID extends Serializable> implements BaseService<M,ID> {

    @Autowired
    protected BaseRepository<M, ID> repository;

    //删除
    @Override
    public void delete(ID id){
        repository.delete(id);
    }

    //批量删除
    @Override
    public void delete(List<M> list){
        repository.delete(list);
    }

    //保存
    @Override
    public M save(M m){
        return repository.save(m);
    }

    //批量保存
    @Override
    public List<M> save(List<M> list){
        return repository.save(list);
    }

    //根据主键查询
    @Override
    public M findOne(ID id){
        return repository.findOne(id);
    }

    @Override
    public List<M> findAll() {
        return repository.findAll();
    }

    @Override
    public List<M> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<M> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
