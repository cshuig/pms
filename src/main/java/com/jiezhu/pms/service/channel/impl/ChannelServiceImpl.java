package com.jiezhu.pms.service.channel.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.comm.util.ShiroUtil;
import com.jiezhu.pms.dao.channel.ChannelBillDAO;
import com.jiezhu.pms.dao.channel.ChannelDAO;
import com.jiezhu.pms.entity.Channel;
import com.jiezhu.pms.entity.ChannelBill;
import com.jiezhu.pms.service.channel.ChannelService;

/**
 * @ClassName: ChannelServiceImpl
 * @Description: 渠道服务商相关操作
 * @author: 张波波
 * @date: 2014年12月25日 上午11:33:05
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelDAO channelDao;
    
    @Autowired
    private ChannelBillDAO channelBillDao;
    
    /**
     * @Description: 检索渠道商信息(分页)
     * @param: 分页信息
     * @return: List<Channel> 渠道商信息列表
     * @Author: 张波波
     * @date: 2014 2014年12月25日
     */
    @Override
    public List<Channel> searchChannelInfoForPage(Page<Channel> page) {
        page.getParams().put("table", "wuba"); // TODO
        return channelDao.searchChannelInfoForPage(page);
    }
    
    /**
     * @Description: 检索渠道商信息
     * @param: 检索条件
     * @return: List<Channel> 渠道商信息列表
     * @Author: 张波波
     * @date: 2014 2014年12月25日
     */
    @Override
    public List<Channel> searchChannelInfo(Channel chan) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", "wuba");
        params.put("chan", chan);
        return channelDao.searchChannelInfo(params);
    }
    
    /**
     * @Description: 新增渠道信息
     * @param: 渠道详细信息
     * @return: 新增渠道ID
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    @Override
    public long insertChannel(Channel chan) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", "wuba");
        // 检索数据库中是否已存在该数据
        Channel chanForCnt = new Channel();
        chanForCnt.setCompanyName(chan.getCompanyName());
        params.put("chan", chanForCnt);
        Integer chanCnt = channelDao.countChannel(params);
        if (!chanCnt.equals(0)) {
            return 0;
        }
        // 插入渠道信息
        chan.setOperatorId(
                String.valueOf(ShiroUtil.getShiroUser().getUserId())); // 操作人员ID
        chan.setStatus("正常"); // TODO 账户运行状态
        params.put("chan", chan);
        return channelDao.insertChannel(params);
    }
    
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
        params.put("chanBill", chanBil);
        return channelBillDao.searchChannelBill(params);
    }

    @Override
    public void updateChannel(Channel chan) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", "wuba");
        params.put("chan", chan);
        channelDao.updateChannel(params);
    };
}
