package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.entity.dms.Meter;
import com.bobandata.iot.entity.dms.Network;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: zhanglingzhi
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:40 2018/7/17.
 */
@Repository
public interface NetworkRepository extends BaseRepository<Network, Integer> {

    @Query("SELECT n FROM Network n")
    Page<Network> selectPageList(Pageable pageable);


    @Query("SELECT m FROM Network m where m.ertuId = ?1 ORDER BY m.ertuId , m.channelId")
    List<Network> findByErtuId(Integer ertuId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Network m WHERE m.ertuId=?1")
    void deleteByErtuId(Integer ertuId);
}
