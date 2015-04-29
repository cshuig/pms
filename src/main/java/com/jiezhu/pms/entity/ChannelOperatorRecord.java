package com.jiezhu.pms.entity;

/**
 * @ClassName: ChannelOperatorRecord
 * @Description: 渠道商信息操作记录
 * @author: 张波波
 * @date: 2014年12月30日
 */
public class ChannelOperatorRecord {

    /* 操作id */
    private String id;
    /* 操作者ID */
    private Integer operatorId;
    /* 渠道商ID */
    private String channelId;
    /* 渠道商账单ID */
    private String channelBillId;
    /* 操作描述 */
    private String description;
    /* 操作时间 */
    private String opTime;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getChannelBillId() {
        return channelBillId;
    }
    public void setChannelBillId(String channelBillId) {
        this.channelBillId = channelBillId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getOpTime() {
        return opTime;
    }
    public void setOpTime(String opTime) {
        this.opTime = opTime;
    }
}
