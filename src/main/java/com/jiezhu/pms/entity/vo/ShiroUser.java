package com.jiezhu.pms.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.jiezhu.pms.entity.Role;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String company;
    private Integer companyId;
    private Integer userId;

    private List<Role> roles;

    public static long expireTime = 300; // 5min

    private String sessionId;

    public String getCompany() {
        return company;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return company + "_" + userName;
    }

}