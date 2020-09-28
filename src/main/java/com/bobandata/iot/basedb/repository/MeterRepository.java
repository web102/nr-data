package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.entity.dms.Meter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:39 2018/7/17.
 */
@Repository
public interface MeterRepository extends BaseRepository<Meter, Integer> {

    @Query("SELECT m FROM Meter m")
    Page<Meter> selectPageList(Pageable pageable);

    @Query("SELECT m FROM Meter m ORDER BY m.ertuId,m.meterNo")
    List<Meter> findAllOrderErtu();

    @Query("SELECT m FROM Meter m where m.ertuId = ?1 ORDER BY m.meterNo")
    List<Meter> findByErtuId(Integer ertuId);
}
