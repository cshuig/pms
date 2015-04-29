package com.jiezhu.pms.dao.shiro;

import java.util.List;

import com.jiezhu.pms.entity.Resource2role;
import com.jiezhu.pms.entity.Resources;
import com.jiezhu.pms.entity.vo.RealmVo;
import com.jiezhu.pms.entity.vo.RoleManageVo;

public interface ResourcesDao {

    void deleteByRoleId(int roleId);

    List<RealmVo> findAllResources();

    List<RoleManageVo> findAllRoleAndResourceByCompanyId(Integer companyId);

    String getPrivilegesByIds(String ids);

    String getPrivilegesByRoleId(Integer roleId);

    List<Resources> getResources();

    void grantResourceRole(List<Resource2role> r2rList);

}
