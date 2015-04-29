package com.jiezhu.pms.entity.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiezhu.pms.entity.Resource2role;

public class RoleManageVo {
    private Integer roleId;
    private String roleName;
    private String privilegeName;
    private Integer companyId;
    private String privilegeIds;

    public void formatPrivileges() {
        String[] ids = privilegeIds.split(",");
        Set set = new HashSet();
        for (String id : ids) {
            int length = id.length();
            while (length > 1) {
                set.add(id);
                id = id.substring(0, length - 1);
                length = id.length();
            }
            set.add(id);
        }
        privilegeIds = set.toString();
        privilegeIds = privilegeIds.substring(1, privilegeIds.length() - 1)
                .replaceAll(" ", "");
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getPrivilegeIds() {
        return privilegeIds;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setPrivilegeIds(String privilegeIds) {
        this.privilegeIds = privilegeIds;// Arrays.asList(privilegeIds.split(" "));
        formatPrivileges();
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Resource2role> toList() {
        List<Resource2role> r2rList = new ArrayList();
        String[] ids = privilegeIds.split(",");
        int size = ids.length;
        for (int i = 0; i < size; i++) {
            Resource2role r2r = new Resource2role();
            r2r.setResourceId(ids[i]);
            r2r.setRoleId(roleId);
            r2rList.add(r2r);
        }
        return r2rList;
    }

}
