package com.jiezhu.pms.entity;

/**
 * @ClassName: Channel
 * @Description: 渠道商
 * @author: 张波波
 * @date: 2014年12月25日 下午2:57:54
 */
public class Channel {

    /* 渠道商内部ID */
    private String id;
    /* 渠道商账号 */
    private String accountId;
    /* 渠道商名称 */
    private String companyName;
    /* 联系人 */
    private String contact;
    /* 联系电话 */
    private String phone;
    /* 房价协议码 */
    private String couponRate;
    /* 合同编号 */
    private String contractId;
    /* 合同开始日期 */
    private String contractStartTime;
    /* 合同结束日期 */
    private String contractEndTime;
    /* 合同扫描件 */
    private String contractScanFile;
    /* 操作人 */
    private String operatorId;
    /* 账户状态 */
    private String status;
    /* 渠道商性质 */
    private String type;
    /* 省 */
    private String province;
    /* 市 */
    private String city;
    /* 街道 */
    private String street;
    /* 具体地址 */
    private String address;
    /* 预留字段 */
    private String flg;
    /* 分页序号 */
    private String rowNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(String couponRate) {
        this.couponRate = couponRate;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(String contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public String getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(String contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public String getContractScanFile() {
        return contractScanFile;
    }

    public void setContractScanFile(String contractScanFile) {
        this.contractScanFile = contractScanFile;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }
}
