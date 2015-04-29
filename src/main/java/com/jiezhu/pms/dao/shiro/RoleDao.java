package com.jiezhu.pms.dao.shiro;

import java.util.List;

import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.vo.RealmVo;

public interface RoleDao {

    void deleteByRoleId(Integer roleId);

    List<Role> findAllRoleByCompanyId(Integer comId);

    List<Role> findAllRoleByEmployeeId(Integer empId);

    Role getByRole(Role role);

    List<Role> getByUserNameAndCompany(RealmVo employee);

    void saveRole(Role role);

}
