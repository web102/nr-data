package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.basedb.bean.Instruct_model_set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:58 2018/7/17.
 */
@Repository
public interface Instruct_model_setRepository  extends BaseRepository<Instruct_model_set, Integer> {
    @Query("SELECT n FROM Instruct_protocol_set n")
    Page<Instruct_model_set> selectPageList(Pageable pageable);

    @Query("SELECT a.instructId FROM Instruct_model_set a WHERE a.modelId = ?1")
    List<Integer>findInstructIdByModelId(Integer modelId);

    @Modifying
    @Query("DELETE FROM Instruct_model_set ims WHERE ims.modelId = ?1 AND ims.instructId = ?2")
    void deleteByModelIdAndInstructId(Integer modelId, Integer instructId);

    @Modifying
    @Query("DELETE FROM Instruct_model_set ims WHERE ims.modelId = ?1")
    void deleteByModelId(Integer modelId);

    @Modifying
    @Query("DELETE FROM Instruct_model_set ims WHERE ims.instructId = ?1")
    void deleteByInstructId(Integer instructId);
}
