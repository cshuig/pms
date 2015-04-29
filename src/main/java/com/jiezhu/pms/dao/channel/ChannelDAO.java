package com.jiezhu.pms.dao.channel;

import java.util.List;
import java.util.Map;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.entity.Channel;

/**
 * @ClassName: ChannelDAO
 * @Description: 渠道商信息相关操作
 * @author: 张波波
 * @date: 2014年12月25日 下午3:27:05
 */
public interface ChannelDAO {

    /**
     * @Description: 渠道商信息列表(分页)
     * @return: List<Channel>
     * @Author: 张波波
     * @date: 2014 2014年12月25日
     */
    public List<Channel> searchChannelInfoForPage(Page<Channel> page);
    
    /**
     * @Description: 渠道商信息列表
     * @return: List<Channel>
     * @Author: 张波波
     * @date: 2014 2014年12月25日
     */
    public List<Channel> searchChannelInfo(Map<String, Object> params);
    
    /**
     * @Description: 检索渠道信息
     * @param: 条件信息
     * @return: Integer 信息数量
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    public Integer countChannel(Map<String, Object> params);
    
    /**
     * @Description: 新增渠道信息
     * @param: 渠道信息
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    public long insertChannel(Map<String, Object> params);
    
    /**
     * @Description: 更新渠道信息
     * @param: 渠道信息
     * @Author: 张波波
     * @date: 2015年1月4日
     */
    public void updateChannel(Map<String, Object> params);
}
