package com.jiezhu.pms.service.channel.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiezhu.pms.dao.channel.ChannelBillDAO;
import com.jiezhu.pms.entity.ChannelBill;
import com.jiezhu.pms.service.channel.ChannelBillService;

/**
 * @ClassName: ChannelServiceImpl
 * @Description: 渠道服务商账单相关操作
 * @author: 张波波
 * @date: 2014年12月25日 上午11:33:05
 */
@Service
public class ChannelBIllServiceImpl implements ChannelBillService {

    @Autowired
    private ChannelBillDAO channelBillDao;
    
    /**
     * @Description: 查询渠道商账单
     * @param: 检索条件
     * @return: 渠道商账单列表
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    public List<ChannelBill> searchChannelBill(ChannelBill chanBil) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", "wuba");
        params.put("chanBil", chanBil);
        return channelBillDao.searchChannelBill(params);
    }
    
    /**
     * @Description: 添加渠道商账单
     * @param: 账单
     * @Author: 张波波
     * @date: 2014年12月31日
     */
    @Override
    public void insertChannelBill(ChannelBill chanBil) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", "wuba");
        params.put("chanBil", chanBil);
        channelBillDao.insertChannelBill(params);
    }
    
    /**
     * @Description: 更新渠道商账单
     * @param: 账单
     * @Author: 张波波
     * @date: 2014年12月31日
     */
    @Override
    public void updateChannelBill(ChannelBill chanBil) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", "wuba");
        params.put("chanBil", chanBil);
        channelBillDao.updateChannelBill(params);
    }
}
