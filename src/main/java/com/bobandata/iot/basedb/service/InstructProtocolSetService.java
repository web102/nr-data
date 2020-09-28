package com.bobandata.iot.basedb.service;

import com.bobandata.iot.entity.dms.Instruct;
import com.bobandata.iot.entity.dms.InstructProtocolSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: lizhipeng
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 16:49 2018/7/17.
 */
public interface InstructProtocolSetService extends BaseService<InstructProtocolSet, Integer> {

    /**
     * 分页查询
     * @param pageable 分页参数
     * @return
     */
    Page<InstructProtocolSet> selectPageList(Pageable pageable);

    //添加规约指令表
    List<InstructProtocolSet>addInstructProtocolSetData(Integer protocolId, Integer[] instructIds);
    //显示所有规约指令
    List<Instruct> protocolInstruct(Integer protocolModelId);

    List<InstructProtocolSet>findByProtocolId(Integer protocolId);

    void deleteByProtocolIdAndInstructId(Integer protocolId, Integer instructId);

    void deleteByProtocolId(Integer protocolId);

    void deleteByInstructId(Integer instructId);
}
