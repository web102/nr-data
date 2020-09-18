package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.basedb.bean.Pulse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:40 2018/7/17.
 */
@Repository
public interface PulseRepository extends BaseRepository<Pulse, Integer> {

    @Query("SELECT p FROM Pulse p")
    Page<Pulse> selectPageList(Pageable pageable);

    List<Pulse> findByMeterId(Integer meterId);

    @Modifying
    @Query("DELETE FROM Pulse p WHERE p.meterId=?1")
    void deleteByMeterId(Integer meterId);

    @Modifying
    @Query("DELETE FROM Pulse p WHERE p.meterId=?1 and p.pulseId=?2")
    void deleteByMeterIdAndId(Integer meterId,Integer pulseId);
}
