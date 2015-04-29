package com.jiezhu.pms.entity.vo;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author
 * @version 1.0
 */
public class CompanyUsernamePasswordToken extends UsernamePasswordToken {

    private static final long serialVersionUID = -2406805646378190346L;

    private String company;

    public CompanyUsernamePasswordToken() {
        super();
    }

    public CompanyUsernamePasswordToken(String username, String password,
            String company) {
        super(username, password, false, null);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
