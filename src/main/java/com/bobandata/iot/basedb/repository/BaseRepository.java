/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.bobandata.iot.basedb.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.io.Serializable;

/**
 * <p>抽象DAO层基类 提供一些简便方法<br/>
 * 具体使用请参考测试用例：{@see cn.guoyukun.spring.jpa.repository.UserRepository}
 * <p/>
 * 想要使用该接口需要在spring配置文件的jpa:repositories中添加
 * factory-class="cn.guoyukun.spring.jpa.repository.support.SimpleBaseRepositoryFactoryBean"
 * <p/>
 * <p>泛型 ： M 表示实体类型；ID表示主键类型
 * <p>User: 郭玉昆
 * <p>Date: 13-1-12 下午4:46
 * <p>Version: 1.0
 */
@NoRepositoryBean
public interface BaseRepository<M,ID extends Serializable> extends JpaRepository<M,ID> {

}
