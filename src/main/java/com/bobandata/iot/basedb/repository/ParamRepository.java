package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.basedb.bean.Param;
import com.bobandata.iot.basedb.bean.Param_model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:26 2018/7/18.
 */
@Repository
public interface ParamRepository extends BaseRepository<Param, Integer> {

    @Query("SELECT p FROM Param p")
    Page<Param> selectPageList(Pageable pageable);

    @Query("SELECT p FROM Param p GROUP BY p.paramType,p.paramId")
    List<Param> findTypes();

    @Query("SELECT p FROM Param p, Param_model_set pms WHERE p.paramId=pms.paramId and pms.modelId=?1")
    List<Param> findParams(Integer modelId);
}
