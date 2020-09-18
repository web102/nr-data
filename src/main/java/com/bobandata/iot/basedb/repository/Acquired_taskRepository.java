package com.bobandata.iot.basedb.repository;


import com.bobandata.iot.basedb.bean.Acquired_task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 17:13 2018/7/17.
 */
public interface Acquired_taskRepository extends BaseRepository<Acquired_task, Integer> {

    @Query("SELECT a FROM Acquired_task a")
    Page<Acquired_task> selectPageList(Pageable pageable);
}
