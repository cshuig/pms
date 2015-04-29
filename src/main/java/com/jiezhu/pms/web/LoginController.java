package com.jiezhu.pms.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jiezhu.pms.comm.util.ShiroUtil;
import com.jiezhu.pms.entity.Company;
import com.jiezhu.pms.entity.Resources;

@Controller
public class LoginController {

    @RequestMapping(value = "/", method = { RequestMethod.GET })
    public ModelAndView toLogin(HttpServletRequest request) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();

        ModelAndView mv;
        if (ShiroUtil.getSubject().isAuthenticated()) {
            mv = new ModelAndView("dashboard");
        } else {
            String company = Resources.getCompany(request.getRequestURL()
                    .toString());
            // test
            if (company == null) {
                company = "jiezhu";
            }
            param.put(Company.COM, company);
            mv = new ModelAndView("login", param);
        }
        return mv;
    }

}