package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.basedb.bean.Param_model;
import com.bobandata.iot.basedb.bean.Param_model_set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:27 2018/7/18.
 */
@Repository
public interface Param_modelRepository extends BaseRepository<Param_model, Integer> {

    @Query("SELECT p FROM Param_model p")
    Page<Param_model> selectPageList(Pageable pageable);

    //根据id查询
    @Query("SELECT p FROM Param_model p WHERE p.modelId = ?1")
    Param_model findModelId(Integer modelId);
}
