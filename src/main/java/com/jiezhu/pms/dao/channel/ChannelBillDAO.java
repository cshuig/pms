package com.jiezhu.pms.dao.channel;

import java.util.List;
import java.util.Map;

import com.jiezhu.pms.entity.ChannelBill;

/**
 * @ClassName: ChannelDAO
 * @Description: 渠道商账单相关操作
 * @author: 张波波
 * @date: 2014年12月26日
 */
public interface ChannelBillDAO {
    /**
     * @Description: 渠道商账单查询
     * @param: 检索条件
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    public List<ChannelBill> searchChannelBill(Map<String, Object> params);
    
    /**
     * @Description: 新增渠道商账单
     * @param: 账单
     * @Author: 张波波
     * @date: 2014年12月31日
     */
    public void insertChannelBill(Map<String, Object> params);
    
    /**
     * @Description: 更新渠道商账单
     * @param: 账单
     * @Author: 张波波
     * @date: 2014年12月31日
     */
    public void updateChannelBill(Map<String, Object> params);
}
