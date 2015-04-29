package org.shiro;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jiezhu.pms.dao.shiro.RoleDao;
import com.jiezhu.pms.dao.shiro.UserDao;
import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.vo.RealmVo;
import com.jiezhu.pms.service.shiro.RealmService;
import com.jiezhu.pms.service.shiro.UserManageService;

/**
 * @ClassName: RealmTestcase
 * @Description: TODO
 * @author: pj
 * @date: 2014年12月25日 下午12:16:36
 */
@ContextConfiguration(locations = { "classpath:/spring-basic.xml" })
@WebAppConfiguration
public class RealmTestcase extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RealmService realmService;
    @Autowired
    private UserManageService userManageService;

    public void getResources() {
        Map<String, String> map = realmService.findResourceMap();
        assertNotNull(map);
    }

    @Test
    public void getRole() {
        String name = "张飞";
        String company = "jiezhu";
        List<Role> list = realmService.findAllRoleByUserNameAndCompany(name,
                company);
        assertNotNull(list);

    }

    @Test
    public void getUser() {
        String name = "张飞";
        String company = "jiezhu";
        RealmVo employee = realmService.findUserInfoByUsernameAndCompany(name,
                company);
        assertNotNull(employee);

    }

    @Test
    public void reCreateFilterChains() {
        // realmService.reCreateFilterChains();
    }

    @Test
    public void sendEmail() throws Exception {
        String accountname = "fangzi";
        String password = "123";
        String email = "zheng108@163.com";
        userManageService.sendEmailToEmplyeeService(accountname, password,
                email);
    }

}
