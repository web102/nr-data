package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.basedb.bean.Instruct_protocol_set;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:46 2018/7/17.
 */
@Repository
public interface Instruct_protocol_setRepository extends BaseRepository<Instruct_protocol_set, Integer>{

    List<Instruct_protocol_set> findByProtocolId(Integer protocolId);

    @Query("SELECT n FROM Instruct_protocol_set n")
    Page<Instruct_protocol_set> selectPageList(Pageable pageable);

    @Query("SELECT a.instructId FROM Instruct_protocol_set a WHERE a.protocolId = ?1")
    List<Integer>findInstructIdByProtocolId(Integer protocolId);

    @Modifying
    @Query("DELETE FROM Instruct_protocol_set ips WHERE ips.protocolId = ?1 AND ips.instructId = ?2")
    void deleteByProtocolIdAndInstructId(Integer protocolId, Integer instructId);

    @Modifying
    @Query("DELETE FROM Instruct_protocol_set ips WHERE ips.protocolId=?1")
    void deleteByProtocolId(Integer protocolId);

    @Modifying
    @Query("DELETE FROM Instruct_protocol_set ips WHERE ips.instructId=?1")
    void deleteByInstructId(Integer instructId);
}
