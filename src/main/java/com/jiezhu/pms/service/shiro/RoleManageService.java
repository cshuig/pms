package com.jiezhu.pms.service.shiro;

import java.util.List;

import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.vo.Menu;
import com.jiezhu.pms.entity.vo.RoleManageVo;

public interface RoleManageService {
    /**
     * 增加role
     *
     * @param rVo
     */
    Role addRole(RoleManageVo rVo);

    /**
     * 删除某个role
     *
     * @param roleId
     */
    Role deleteByRoleId(Integer roleId);

    /**
     * 获取资源权限列表
     *
     * @return
     */
    List<Menu> getAllPrivileges();

    /**
     * 获取用户角色权限列表
     *
     * @Title: getAllRoleAndPrivilege
     * @Description: TODO
     * @param companyId
     * @return
     * @return: List<RoleManageVo>
     */
    List<RoleManageVo> getAllRoleAndPrivilege(Integer companyId);

    String getPrivilegeIdsByRole(Integer roleId);

    String getPrivilegeNames(String[] ids);

    /**
     * 获取role的资源列表
     *
     * @param roleId
     * @return
     */
    List<Menu> getResourcesByRoleId(Integer roleId);

    Role getRole(Integer roleId);

    /**
     * 更新role
     *
     * @param rVo
     */
    Role updateRole(RoleManageVo rVo);
}
