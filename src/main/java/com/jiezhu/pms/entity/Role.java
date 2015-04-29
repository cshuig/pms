package com.jiezhu.pms.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private Integer companyId;
    private boolean flag;

    public Integer getCompanyId() {
        return companyId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
