package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.entity.dms.InstructProtocolSet;
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
public interface InstructProtocolSetRepository extends BaseRepository<InstructProtocolSet, Integer>{

    @Query("SELECT n FROM InstructProtocolSet n where n.protocolId = ?1 order by n.instructId")
    List<InstructProtocolSet> findByProtocolId(Integer protocolId);

    @Query("SELECT n FROM InstructProtocolSet n")
    Page<InstructProtocolSet> selectPageList(Pageable pageable);

    @Query("SELECT a.instructId FROM InstructProtocolSet a WHERE a.protocolId = ?1 order by a.instructId")
    List<Integer>findInstructIdByProtocolId(Integer protocolId);

    @Modifying
    @Query("DELETE FROM InstructProtocolSet ips WHERE ips.protocolId = ?1 AND ips.instructId = ?2")
    void deleteByProtocolIdAndInstructId(Integer protocolId, Integer instructId);

    @Modifying
    @Query("DELETE FROM InstructProtocolSet ips WHERE ips.protocolId=?1")
    void deleteByProtocolId(Integer protocolId);

    @Modifying
    @Query("DELETE FROM InstructProtocolSet ips WHERE ips.instructId=?1")
    void deleteByInstructId(Integer instructId);
}
