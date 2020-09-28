package com.bobandata.iot.basedb.repository;

import com.bobandata.iot.entity.dms.Network;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
