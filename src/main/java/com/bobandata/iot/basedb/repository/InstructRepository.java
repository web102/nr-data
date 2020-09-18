package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.basedb.bean.Instruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 11:58 2018/7/17.
 */
@Repository
public interface InstructRepository extends BaseRepository<Instruct,Integer>{
    @Query("SELECT i FROM Instruct i")
    Page<Instruct> selectPageList(Pageable pageable);

    @Query("SELECT i FROM Instruct i GROUP BY i.instructType,i.instructId")
    List<Instruct> findAllInstructTypes();

    List<Instruct> findByInstructName(String instructName);
}
