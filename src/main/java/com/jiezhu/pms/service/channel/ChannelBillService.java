package com.jiezhu.pms.service.channel;

import java.util.List;

import com.jiezhu.pms.entity.ChannelBill;

/**
 * @ClassName: ChannelService
 * @Description: 渠道服务商账单相关操作
 * @author: 张波波
 * @date: 2014年12月25日 上午11:33:05
 */
public interface ChannelBillService {

    /**
     * @Description: 查询渠道商账单
     * @param: 检索条件
     * @return: 渠道商账单列表
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    List<ChannelBill> searchChannelBill(ChannelBill chanBil);
    
    /**
     * @Description: 添加渠道商账单
     * @param: 账单
     * @Author: 张波波
     * @date: 2014年12月31日
     */
    void insertChannelBill(ChannelBill chanBil);
    
    /**
     * @Description: 更新渠道商账单
     * @param: 账单
     * @Author: 张波波
     * @date: 2014年12月31日
     */
    void updateChannelBill(ChannelBill chanBil);
}
