package com.jiezhu.pms.service.shiro.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiezhu.pms.dao.shiro.ResourcesDao;
import com.jiezhu.pms.dao.shiro.RoleDao;
import com.jiezhu.pms.dao.shiro.UserDao;
import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.vo.RealmVo;
import com.jiezhu.pms.service.shiro.RealmService;

@Service
public class RealmServiceImpl implements RealmService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ResourcesDao resourceDao;

    // @Resource
    // private ShiroDbFilterFactoryBean shiroFilterFactoryBean;
    // private String ROLES = "roles[%s]";
    private String OROLE = "authc,oRole[%s]";
    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Role> findAllRoleByUserNameAndCompany(String username,
            String company) {
        RealmVo employee = new RealmVo();
        employee.setUsername(username);
        employee.setCompany(company);
        return roleDao.getByUserNameAndCompany(employee);
    }

    /*
     *  url 跟权限（角色）的映射关系
     * /a.html=roles['1','user','guset']
     * /b.html=roles['1','user','guset']
     */
    @Override
    public Map<String, String> findResourceMap() {
        Map<String, String> rsMap = new HashMap<String, String>();
        List<RealmVo> resourceList = resourceDao.findAllResources();
        for (RealmVo resource : resourceList) {
            String key = resource.getUrl().trim();
            String value = resource.getRoleId() + "";
            if (rsMap.containsKey(key)) {
                String filter = rsMap.get(key);
                int indx = filter.indexOf("[") + 1;
                value = filter.substring(0, indx) + value + ","
                        + filter.substring(indx);
                // value = String.format(ROLES, "guset");
            } else {
                value = String.format(OROLE, value);
            }

            rsMap.put(key, value);
        }
        return rsMap;

    }

    @Override
    public RealmVo findUserInfoByUsernameAndCompany(String username,
            String company) {
        RealmVo employee = new RealmVo();
        employee.setUsername(username);
        employee.setCompany(company);
        return userDao.getByUserNameAndCompany(employee);
    }

}
