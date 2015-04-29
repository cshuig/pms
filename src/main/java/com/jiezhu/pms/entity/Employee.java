package com.jiezhu.pms.entity;

import java.util.Date;

public class Employee {
    private Integer id;
    private String accountname;
    private String name;
    private Integer branchCompanyId;
    private Integer departmentId;
    private String mobile;
    private String email;
    private String password;
    private Integer companyId;

    public static String NAME = "userName";

    private Integer flag;

    private Date lastSigninTime;

    public String getAccountname() {
        return accountname;
    }

    public Integer getBranchCompanyId() {
        return branchCompanyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getEmail() {
        return email;
    }

    public Integer getFlag() {
        return flag;
    }

    public Integer getId() {
        return id;
    }

    public Date getLastSigninTime() {
        return lastSigninTime;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public void setBranchCompanyId(Integer branchCompanyId) {
        this.branchCompanyId = branchCompanyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastSigninTime(Date lastSigninTime) {
        this.lastSigninTime = lastSigninTime;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
