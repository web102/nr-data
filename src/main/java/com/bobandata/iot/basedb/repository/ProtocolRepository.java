package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.entity.dms.Protocol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liutuo
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:12 2018/7/16.
 */
@Repository
public interface ProtocolRepository extends BaseRepository<Protocol, Integer>{

    @Query("SELECT p FROM Protocol p")
    Page<Protocol> selectPageList(Pageable pageable);

    List<Protocol> findByProtocolName(String protocolName);
}
