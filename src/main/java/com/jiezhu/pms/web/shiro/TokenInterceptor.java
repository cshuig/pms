package com.jiezhu.pms.web.shiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jiezhu.pms.entity.Company;
import com.jiezhu.pms.entity.Employee;
import com.jiezhu.pms.entity.vo.ShiroUser;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    protected transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            ModelMap model = modelAndView.getModelMap();
            if (model != null && !model.containsKey(Company.COM)) {
                Subject subject = SecurityUtils.getSubject();
                if (subject != null && subject.isAuthenticated()) {
                    ShiroUser sUser = (ShiroUser) subject.getPrincipal();
                    modelAndView.addObject(Company.COM, sUser.getCompany());
                    modelAndView.addObject(Employee.NAME, sUser.getUserName());
                }

            }
        }
    }

}
