package com.jiezhu.com;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jiezhu.pms.comm.util.JsonUtils;
import com.jiezhu.pms.dao.shiro.RoleDao;
import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.vo.Menu;
import com.jiezhu.pms.entity.vo.RoleManageVo;
import com.jiezhu.pms.service.shiro.RoleManageService;

/**
 * @Description:
 *
 * @author dell
 * @version TODO
 */
@ContextConfiguration(locations = { "classpath:/spring-basic.xml" })
@WebAppConfiguration
public class RoleTestcase extends AbstractJUnit4SpringContextTests {

    @Autowired
    private RoleManageService roleManageService;

    @Autowired
    private RoleDao roleDao;

    @Test
    public void addrole() {
        RoleManageVo rVo = new RoleManageVo();

        new ArrayList() {
            {
                add(3);
                add(31);
            }
        };
        // rVo.setPrivilegeIds(resourceList);
        rVo.setRoleName("操作员");
        rVo.setCompanyId(1);
        roleManageService.addRole(rVo);

        // List<Resource2role> list = roleManageService
        // .getResourcesByRoleId(roleId);
        // assertEquals(list.size(), resourceList.size());

    }

    @Test
    public void deleteByRole() {
        Integer roleId = 6;
        roleManageService.deleteByRoleId(roleId);
    }

    @Test
    public void getAllPrivilege() {
        List<Menu> menu = roleManageService.getAllPrivileges();
        assertNotNull(menu);
    }

    @Test
    public void getPrivileges() {
        String rs = "2,31,311";
        String[] ids = rs.split(" ");
        String names = roleManageService.getPrivilegeNames(ids);
        System.out.println(names);
    }

    @Test
    public void list() {
        Integer companyId = 1;
        List<RoleManageVo> list = roleManageService
                .getAllRoleAndPrivilege(companyId);
        String json = JsonUtils.list2JsonString(list);
        assertNotNull(json);
    }

    @Test
    public void updateResourceAndRole() {
        Integer roleId = 7;
        RoleManageVo rVo = new RoleManageVo();
        rVo.setRoleId(roleId);
        rVo.setPrivilegeIds("3,31,311");
        Role role = roleManageService.updateRole(rVo);
        assertNotNull(role);
    }
}
