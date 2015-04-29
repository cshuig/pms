package com.jiezhu.pms.entity;

public class Company {
    private Integer id;
    private String name;
    private String company_id;

    public static String COM = "company";

    public String getCompany_id() {
        return company_id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
