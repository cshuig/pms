package com.jiezhu.pms.dao.channel;

import java.util.List;
import java.util.Map;

import com.jiezhu.pms.entity.ChannelOperatorRecord;

/**
 * @ClassName: ChannelOpeRecordDAO
 * @Description: 渠道商操作记录相关操作
 * @author: 张波波
 * @date: 2014年12月30日
 */
public interface ChannelOpeRecordDAO {

    /**
     * @Description: 新增操作记录
     * @param: 操作记录
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    public void insertRecord(Map<String, Object> chanOp);
    
    /**
     * @Description: 检索操作记录
     * @param: 检索条件
     * @return 操作记录列表
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    public List<ChannelOperatorRecord> serachOperationList(
            Map<String, Object> chanOp);
}
