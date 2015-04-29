package com.jiezhu.pms.service.shiro;

import java.util.List;
import java.util.Map;

import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.vo.RealmVo;

public interface RealmService {

    /**
     * 根据用户名及公司名查找用户所有角色信息
     *
     * @param employee
     * @return
     */
    List<Role> findAllRoleByUserNameAndCompany(String username, String company);

    /**
     * 查找所有url资源
     * 
     * @return
     */
    Map<String, String> findResourceMap();

    /**
     * 根据用户名及公司名查找用户信息
     *
     * @param employee
     * @return
     */
    RealmVo findUserInfoByUsernameAndCompany(String username, String company);

}
