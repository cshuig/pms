package com.jiezhu.pms.service.channel;

import java.util.List;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.entity.Channel;

/**
 * @ClassName: ChannelService
 * @Description: 渠道服务商账单相关操作
 * @author: 张波波
 * @date: 2014年12月25日 上午11:33:05
 */
public interface ChannelService {

    /**
     * @Description: 检索渠道商信息(分页)
     * @param: 分页信息
     * @return: List<Channel> 渠道商信息列表
     * @Author: 张波波
     * @date: 2014 2014年12月25日
     */
    public List<Channel> searchChannelInfoForPage(Page<Channel> page);
    
    /**
     * @Description: 检索渠道商信息
     * @param: 检索条件
     * @return: List<Channel> 渠道商信息列表
     * @Author: 张波波
     * @date: 2014 2014年12月25日
     */
    public List<Channel> searchChannelInfo(Channel chan);
    
    /**
     * @Description: 新增渠道信息
     * @param: 渠道详细信息
     * @return: 新增渠道ID
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    public long insertChannel(Channel chan);
    
    /**
     * @Description: 更新渠道信息
     * @param: 渠道信息
     * @Author: 张波波
     * @date: 2015年1月4日
     */
    public void updateChannel(Channel chan);
}
