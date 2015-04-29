package com.jiezhu.pms.entity;

import java.util.Date;

public class UserOperationRecords {
    private Integer id;
    private Integer employeeId;
    private Integer operatorId;
    private Date opTime;
    private String description;

    public UserOperationRecords() {
        super();
    }

    public UserOperationRecords(Integer employeeId, Integer operatorId,
            Date opTime, String description) {
        this.employeeId = employeeId;
        this.operatorId = operatorId;
        this.opTime = opTime;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
