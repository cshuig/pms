package com.jiezhu.pms.service.channel.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiezhu.pms.comm.util.DateUtil;
import com.jiezhu.pms.comm.util.ShiroUtil;
import com.jiezhu.pms.dao.channel.ChannelOpeRecordDAO;
import com.jiezhu.pms.entity.ChannelOperatorRecord;
import com.jiezhu.pms.service.channel.ChannelOpeRecordService;

/**
 * @ClassName: ChannelOpeRecordService
 * @Description: 渠道商信息操作记录相关操作
 * @author: 张波波
 * @date: 2014年12月30日
 */
@Log4j
@Service
public class ChannelOpeRecordServiceImpl implements ChannelOpeRecordService {

    @Autowired
    private ChannelOpeRecordDAO channelOpeRecordDAO;

    /**
     * @Description: 新增操作记录
     * @param detail
     *            操作描述
     * @date: 2014年12月30日
     */
    @Override
    @Transactional
    public void insertRecord(Map<String, String> detail) {
        ChannelOperatorRecord ope = new ChannelOperatorRecord();
        String opId = String.valueOf(ShiroUtil.getShiroUser().getUserId());
        String now = DateUtil.getCurrentTimeStamp("YYYY-MM-DD HH:MM:SS");
        StringBuffer description = new StringBuffer();
        String operator = detail.get("operator");
        if ("addChan".equals(operator)) {
            ope.setChannelId(detail.get("chanId"));
            description.append(opId + "(" + now + "):" + "新增渠道商"
                    + detail.get("chanName"));
        } else if ("addChanBil".equals(operator)) {
            ope.setChannelBillId(detail.get("chanBilId"));
            description.append(opId + "(" + now + "):" + "新增账单"); // TODO 如何描述账单
        }
        ope.setOperatorId(ShiroUtil.getShiroUser().getUserId()); // 操作者
        ope.setDescription(description.toString()); // 操作描述
        ope.setOpTime(now); // 操作日期

        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("table", "wuba"); // TODO
            params.put("ope", ope);
            channelOpeRecordDAO.insertRecord(params);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("新增操作记录发生错误!");
        }
    }

    /**
     * @Description: 检索操作记录
     * @param ope
     *            检索条件
     * @return 操作记录列表
     * @date: 2014年12月30日
     */
    @Override
    public List<ChannelOperatorRecord> serachOperationList(
            ChannelOperatorRecord ope) {
        Map<String, Object> params = new HashMap<String, Object>();
        // ShiroUtil.getShiroUser().getCompany();
        params.put("table", "wuba"); // TODO
        params.put("ope", ope);
        return channelOpeRecordDAO.serachOperationList(params);
    }
}
