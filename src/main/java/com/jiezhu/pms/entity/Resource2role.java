package com.jiezhu.pms.entity;

public class Resource2role {
    private Integer id;
    private Integer resourceId;
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = Integer.parseInt(resourceId);
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
