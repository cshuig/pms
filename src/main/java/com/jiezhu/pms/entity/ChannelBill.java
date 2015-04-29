package com.jiezhu.pms.entity;

/**
 * @ClassName: ChannelBill
 * @Description: 渠道商账单
 * @author: 张波波
 * @date: 2014年12月26日
 */
public class ChannelBill {

    /* 账单编号 */
    private String id;
    /* 渠道商账号 */
    private String channelId;
    /* 账单开始日 */
    private String billStartTime;
    /* 账单结束日 */
    private String billEndTime;
    /* 财务类型 */
    private String type;
    /* 支付方式 */
    private String paymentType;
    /* 借方(会计) */
    private String debit;
    /* 贷方(会计) */
    private String credit;
    /* 单据号 */
    private String documentId;
    /* 账单状态 */
    private String status;
    /* 操作员 */
    private String operatorId;
    /* 记账时间 */
    private String accountingTime ;
    /* 备注 */
    private String remarks;
    /* 扩展字段 */
    private String flag;
    /* 序列号 */
    private String rowNum;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getBillStartTime() {
        return billStartTime;
    }
    public void setBillStartTime(String billStartTime) {
        this.billStartTime = billStartTime;
    }
    public String getBillEndTime() {
        return billEndTime;
    }
    public void setBillEndTime(String billEndTime) {
        this.billEndTime = billEndTime;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDebit() {
        return debit;
    }
    public void setDebit(String debit) {
        this.debit = debit;
    }
    public String getCredit() {
        return credit;
    }
    public void setCredit(String credit) {
        this.credit = credit;
    }
    public String getDocumentId() {
        return documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    public String getAccountingTime() {
        return accountingTime;
    }
    public void setAccountingTime(String accountingTime) {
        this.accountingTime = accountingTime;
    }
    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getFlg() {
        return flag;
    }
    public void setFlg(String flag) {
        this.flag = flag;
    }
    public String getRowNum() {
        return rowNum;
    }
    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }
}
