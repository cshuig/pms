package com.jiezhu.pms.entity;

import java.util.Date;

public class RoleOperationRecords {
    private Integer id;
    private Integer roleId;
    private Integer operatorId;
    private Date opTime;
    private String description;

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public Date getOpTime() {
        return opTime;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
