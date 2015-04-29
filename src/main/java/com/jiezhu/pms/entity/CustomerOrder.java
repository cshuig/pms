package com.jiezhu.pms.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerOrder {
    private Integer id;
    private String orderNumber;
    private Integer channelId;
    private Integer branchCompanyId;
    private String roomNumber;
    private String roomType;// enum
    private Integer totalRoom;
    private Date visitingTime;
    private BigDecimal housePrice;
    private String visitor;
    private Integer customerId;
    private String gender;// enum
    private String phone;
    private Integer customerManagerId;
    private Date checkinTime;
    private Date checkoutTime;
    private String paymentType;// enum
    private String paymentSerialNumber;
    private BigDecimal deposit;
    private Integer serviceAgentId;
    private String visitedRoomNumber;
    private String serviceAgentRemarks;
    private String cumstomManagerRemarks;
    private Integer rootOrderId;
    private Integer parentOrderId;
    private Integer childOrder;
    private String status;// enum
    private Date orderTime;
    private char flag;
    private String company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getBranchCompanyId() {
        return branchCompanyId;
    }

    public void setBranchCompanyId(Integer branchCompanyId) {
        this.branchCompanyId = branchCompanyId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(Integer totalRoom) {
        this.totalRoom = totalRoom;
    }

    public Date getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(Date visitingTime) {
        this.visitingTime = visitingTime;
    }

    public BigDecimal getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(BigDecimal housePrice) {
        this.housePrice = housePrice;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Date getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Date checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentSerialNumber() {
        return paymentSerialNumber;
    }

    public void setPaymentSerialNumber(String paymentSerialNumber) {
        this.paymentSerialNumber = paymentSerialNumber;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Integer getServiceAgentId() {
        return serviceAgentId;
    }

    public void setServiceAgentId(Integer serviceAgentId) {
        this.serviceAgentId = serviceAgentId;
    }

    public String getVisitedRoomNumber() {
        return visitedRoomNumber;
    }

    public void setVisitedRoomNumber(String visitedRoomNumber) {
        this.visitedRoomNumber = visitedRoomNumber;
    }

    public String getServiceAgentRemarks() {
        return serviceAgentRemarks;
    }

    public void setServiceAgentRemarks(String serviceAgentRemarks) {
        this.serviceAgentRemarks = serviceAgentRemarks;
    }

    public String getCumstomManagerRemarks() {
        return cumstomManagerRemarks;
    }

    public void setCumstomManagerRemarks(String cumstomManagerRemarks) {
        this.cumstomManagerRemarks = cumstomManagerRemarks;
    }

    public Integer getRootOrderId() {
        return rootOrderId;
    }

    public void setRootOrderId(Integer rootOrderId) {
        this.rootOrderId = rootOrderId;
    }

    public Integer getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(Integer parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    public Integer getChildOrder() {
        return childOrder;
    }

    public void setChildOrder(Integer childOrder) {
        this.childOrder = childOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public char getFlag() {
        return flag;
    }

    public void setFlag(char flag) {
        this.flag = flag;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}
