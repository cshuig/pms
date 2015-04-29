package com.jiezhu.pms.service.shiro.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiezhu.pms.comm.util.ShiroUtil;
import com.jiezhu.pms.dao.shiro.ResourcesDao;
import com.jiezhu.pms.dao.shiro.RoleDao;
import com.jiezhu.pms.dao.shiro.UserDao;
import com.jiezhu.pms.entity.Resource2role;
import com.jiezhu.pms.entity.Resources;
import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.vo.Menu;
import com.jiezhu.pms.entity.vo.RoleManageVo;
import com.jiezhu.pms.service.shiro.RoleManageService;

@Service
public class RoleManageServiceImpl implements RoleManageService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ResourcesDao resourceDao;

    @Override
    @Transactional
    public Role addRole(RoleManageVo rVo) {

        Role role = new Role();
        role.setCompanyId(rVo.getCompanyId());
        role.setDescription(rVo.getRoleName());
        role.setName(rVo.getRoleName());
        if (roleDao.getByRole(role) == null) {
            roleDao.saveRole(role);
            rVo.setRoleId(role.getId());
            List<Resource2role> r2rList = rVo.toList();
            resourceDao.grantResourceRole(r2rList);
            ShiroUtil.rebuildFilterChain();

        } else {
            // TODO roleName exist
        }
        return role;
    }

    @Override
    public Role deleteByRoleId(Integer roleId) {
        Role role = new Role();
        role.setId(roleId);
        role = roleDao.getByRole(role);
        if (role != null) {
            resourceDao.deleteByRoleId(roleId);
            userDao.deleteByRoleId(roleId);
            roleDao.deleteByRoleId(roleId);

        }
        return role;
    }

    @Override
    public List<Menu> getAllPrivileges() {
        Map<String, Menu> menuMap = getResourceMap();
        List<Menu> menuList = menuMap.get("0").getChildren();
        for (Menu menu : menuList) {
            // menu.setChildren(menu.getLeaf());
        }
        return menuList;
    }

    @Override
    public List<RoleManageVo> getAllRoleAndPrivilege(Integer companyId) {
        List<RoleManageVo> rvoList = resourceDao
                .findAllRoleAndResourceByCompanyId(companyId);
        // TODO
        List<RoleManageVo> roleList = new ArrayList<RoleManageVo>();
        Map<Integer, RoleManageVo> resultMap = new TreeMap<Integer, RoleManageVo>();
        for (RoleManageVo rvo : rvoList) {
            Integer roleId = rvo.getRoleId();
            if (resultMap.containsKey(roleId)) {
                RoleManageVo vo = resultMap.get(roleId);
                String privilege = String.format("%s,%s",
                        vo.getPrivilegeName(), rvo.getPrivilegeName());
                vo.setPrivilegeName(privilege.trim());
            } else {
                resultMap.put(roleId, rvo);
            }
        }
        Set<Entry<Integer, RoleManageVo>> set = resultMap.entrySet();
        for (Entry<Integer, RoleManageVo> entry : set) {
            roleList.add(entry.getValue());
        }

        return roleList;
    }

    @Override
    public String getPrivilegeIdsByRole(Integer roleId) {
        return resourceDao.getPrivilegesByRoleId(roleId);
    }

    @Override
    public String getPrivilegeNames(String[] Ids) {
        String ids = "";
        StringBuffer sb = new StringBuffer();
        sb.append(Ids[0]);
        for (int i = 1; i < Ids.length; i++) {
            if (!"".equals(Ids[i])) {
                sb.append(",").append(Ids[i]);
            }
        }
        ids = sb.toString();
        return resourceDao.getPrivilegesByIds(ids).replaceAll(" ", "");
    }

    private Map<String, Menu> getResourceMap() {
        List<Resources> rsList = resourceDao.getResources();
        Map<String, Menu> menuMap = new HashMap();
        menuMap.put("0", new Menu());
        for (Resources rs : rsList) {
            String pid = rs.getParentId() + "";
            Integer id = rs.getId();
            Menu menu = new Menu();
            menu.setId(id);
            menu.setName(rs.getPrivilegeName());
            menuMap.put("" + id, menu);
            if (menuMap.containsKey(pid)) {
                menuMap.get(pid).addChild(menu);
            }
        }
        return menuMap;
    }

    @Override
    public List<Menu> getResourcesByRoleId(Integer roleId) {
        String ids = resourceDao.getPrivilegesByRoleId(roleId);
        Map<String, Menu> menuMap = getResourceMap();

        for (String id : ids.split(",")) {
            if (menuMap.containsKey(id)) {
                menuMap.get(id).setFlag();
            }
        }
        List<Menu> menuList = menuMap.get("0").getChildren();
        for (Menu menu : menuList) {
            // menu.setChildren(menu.getLeaf());
        }
        return menuList;
    }

    @Override
    public Role getRole(Integer roleId) {
        Role role = new Role();
        role.setId(roleId);
        return roleDao.getByRole(role);
    }

    @Override
    @Transactional
    public Role updateRole(RoleManageVo rVo) {
        Integer roleId = rVo.getRoleId();
        Role role = new Role();
        role.setId(roleId);
        role = roleDao.getByRole(role);
        if (role != null) {
            resourceDao.deleteByRoleId(roleId);
            List<Resource2role> r2rList = rVo.toList();
            resourceDao.grantResourceRole(r2rList);
            ShiroUtil.rebuildFilterChain();
        }
        return role;
    }
}
