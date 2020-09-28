package com.bobandata.iot.basedb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: liutuo
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 9:49 2018/7/17.
 */
public interface BaseService<M, ID extends Serializable> {

    //删除
    void delete(ID id);

    //批量删除
    void delete(List<M> list);

    //保存
    M save(M m);

    //批量保存
    List<M> save(List<M> list);

    //根据主键查询
    M findOne(ID id);

    //查询所有
    List<M> findAll();

    //查询所有
    List<M> findAll(Sort sort);
    /**
     * 分页排序获取数据
     * 禁止使用该接口进行count操作
     * Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.DESC,"id"));
     * @param pageable
     * @return
     */
    Page<M> findAll(Pageable pageable);

}
