package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.basedb.bean.Param_model_set;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:27 2018/7/18.
 */
@Transactional
@Repository
public interface Param_model_setRepository extends BaseRepository<Param_model_set, Integer> {
    @Query("SELECT a.paramId FROM Param_model_set a WHERE a.modelId=?1")
    List<Integer> findParamIdByModelId(Integer modelId);

    @Modifying
    @Query("DELETE FROM Param_model_set a WHERE a.modelId=?1")
    void deleteParamIdByModelId(Integer modelId);
}