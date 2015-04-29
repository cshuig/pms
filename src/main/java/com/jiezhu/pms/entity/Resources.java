package com.jiezhu.pms.entity;

public class Resources {
    public static String getCompany(String url) {
        String company = null;
        int begin = url.indexOf("://") + 3;
        if (begin != -1) {
            company = url.substring(begin);
        }
        int end = company.indexOf(".");
        if (end != -1) {
            company = company.substring(0, end);
        } else {
            company = null;
        }
        return company;
    }

    private Integer id;
    private String url;
    private String type;
    private Integer parentId;
    private String menuName;

    private String privilegeName;

    public Integer getId() {
        return id;
    }

    public String getMenuName() {
        return menuName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
