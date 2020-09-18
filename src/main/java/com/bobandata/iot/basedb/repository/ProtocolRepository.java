package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.basedb.bean.Protocol;
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

    @Query("SELECT p FROM Protocol p where p.protocolModelId=?1")
    List<Protocol>  findByModelId(Integer modelId);

    @Modifying
    @Query("DELETE FROM Protocol p WHERE p.protocolModelId = ?1")
    void deleteByModelId(Integer modelId);
}
