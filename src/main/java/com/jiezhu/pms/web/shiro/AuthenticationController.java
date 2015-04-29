package com.jiezhu.pms.web.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.jiezhu.pms.comm.util.ShiroUtil;
import com.jiezhu.pms.entity.Company;
import com.jiezhu.pms.entity.vo.CompanyUsernamePasswordToken;
import com.jiezhu.pms.entity.vo.ShiroUser;

/**
 * @author
 * @version 1.0
 * @Description 身份验证
 */
@Controller
public class AuthenticationController extends MultiActionController {
    private String LOGIN = "/";

    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 登录身份验证
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/dashboard")
    public ModelAndView auth(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();

        log.debug("user principal: {}", new Object[] { subject.getPrincipal() });
        log.debug("user authenticated: {}",
                new Object[] { subject.isAuthenticated() });
        Map<String, Object> map = new HashMap<String, Object>();
        if (!subject.isAuthenticated()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String company = request.getParameter(Company.COM);
            if (username == null || password == null || company == null) {
                return new ModelAndView("redirect:" + LOGIN);
            }

            CompanyUsernamePasswordToken objToken = new CompanyUsernamePasswordToken(
                    username, password, company);
            try {
                subject.login(objToken);

                // ShiroUser value = (ShiroUser) subject.getPrincipal();
                // ShiroUtil.setToken(key, value);
                // response.addCookie(new Cookie(ShiroUser.TOKEN, key));

            } catch (UnknownAccountException uae) {
                log.error("不存在用户[{}]", new Object[] { objToken.getPrincipal() });
                return new ModelAndView("redirect:" + LOGIN);
                // throw new UnknownAccountException(String.format("不存在用户[%s]",
                // objToken.getPrincipal()));
            } catch (IncorrectCredentialsException ice) {
                log.error("用户[{}]密码错误",
                        new Object[] { objToken.getPrincipal() });
                return new ModelAndView("redirect:" + LOGIN);
                // throw new IncorrectCredentialsException(String.format(
                // "用户[%s]密码错误", objToken.getPrincipal()));
            } catch (DisabledAccountException dae) {
                log.error(dae.getMessage(), dae);
                throw new DisabledAccountException(dae.getMessage(), dae);
            } catch (Throwable t) {
                log.error(t.getMessage(), t);
                throw new AuthenticationException(t.getMessage());
            }
        }

        return new ModelAndView("dashboard", map);
    }

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ShiroUser sUser = ShiroUtil.getShiroUser();
        map.put("companyId", sUser.getCompanyId());
        map.put("sessionId", ShiroUtil.getSessionId());
        return new ModelAndView("dashboard", map);
    }

    /**
     * 登出
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        SecurityUtils.getSubject().logout();
        return new ModelAndView("redirect:" + LOGIN);
    }

}
