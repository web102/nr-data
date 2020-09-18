package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.basedb.bean.Instruct_model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:13 2018/7/17.
 */
@Repository
public interface Instruct_modelRepository extends BaseRepository<Instruct_model, Integer>{

    @Query("SELECT i FROM Instruct_model i")
    Page<Instruct_model> selectPageList(Pageable pageable);

    List<Instruct_model> findByModelName(String modelName);

}
