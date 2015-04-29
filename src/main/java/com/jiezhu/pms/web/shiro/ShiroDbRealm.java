package com.jiezhu.pms.web.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.vo.CompanyUsernamePasswordToken;
import com.jiezhu.pms.entity.vo.RealmVo;
import com.jiezhu.pms.entity.vo.ShiroUser;
import com.jiezhu.pms.service.shiro.RealmService;

public class ShiroDbRealm extends AuthorizingRealm {

    private RealmService realmService;

    /**
     * @Title: doGetAuthenticationInfo
     * @Description: 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String userName = token.getPrincipal().toString();
        CompanyUsernamePasswordToken ctoken = (CompanyUsernamePasswordToken) token;
        String company = ctoken.getCompany();

        RealmVo employee = realmService.findUserInfoByUsernameAndCompany(
                userName, company);
        if (employee != null) {

            ShiroUser user = new ShiroUser();
            user.setUserId(employee.getUserId());
            user.setUserName(userName);
            user.setCompany(company);
            user.setCompanyId(employee.getCompanyId());

            // status

            List<Role> roles = realmService.findAllRoleByUserNameAndCompany(
                    userName, company);
            user.setRoles(roles);

            return new SimpleAuthenticationInfo(user, employee.getPassword(),
                    getName());
        } else
            return null;
    }

    /**
     * @Title: doGetAuthorizationInfo
     * @Description: 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName())
                .iterator().next();
        String username = shiroUser.getUserName();
        String company = shiroUser.getCompany();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Role> roles = realmService.findAllRoleByUserNameAndCompany(
                username, company);
        for (Role role : roles) {
            info.addRole(role.getId() + "");
        }

        return info;
    }

    public RealmService getRealmService() {
        return realmService;
    }

    public void setRealmService(RealmService realmService) {
        this.realmService = realmService;
    }

}
