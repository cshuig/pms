package com.jiezhu.pms.service.channel;

import java.util.List;
import java.util.Map;

import com.jiezhu.pms.entity.ChannelOperatorRecord;

/**
 * @ClassName: ChannelOpeRecordService
 * @Description: 渠道商信息操作记录相关操作
 * @author: 张波波
 * @date: 2014年12月30日
 */
public interface ChannelOpeRecordService {

    /**
     * @Description: 新增操作记录
     * @param detail 操作描述
     * @date: 2014年12月30日
     */
    void insertRecord(Map<String, String> detail);
    
    /**
     * @Description: 检索操作记录
     * @param ope 检索条件
     * @return 操作记录列表
     * @date: 2014年12月30日
     */
    List<ChannelOperatorRecord> serachOperationList(
            ChannelOperatorRecord ope);
}
