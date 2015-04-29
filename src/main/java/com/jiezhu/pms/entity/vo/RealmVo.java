package com.jiezhu.pms.entity.vo;

public class RealmVo {

    private String username;
    private String password;

    private Integer companyId;
    private String company;

    private String url;
    private Integer resourceId;

    private Integer RoleId;
    private Integer userId;

    public String getCompany() {
        return company;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getPassword() {
        return password;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public Integer getRoleId() {
        return RoleId;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
