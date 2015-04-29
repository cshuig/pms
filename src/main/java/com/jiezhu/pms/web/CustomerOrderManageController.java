package com.jiezhu.pms.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.entity.vo.CustomerOrderVo;
import com.jiezhu.pms.service.order.CustomerOrderManageService;
import com.jiezhu.pms.web.comm._BaseController;

/**
 * @ClassName: CustomerOrderManageController
 * @Description: 预约单管理
 * @author: wff
 * @date: 2015年1月4日 上午11:05:05
 */
@Controller
@RequestMapping("/order")
public class CustomerOrderManageController extends _BaseController {
    @Autowired
    private CustomerOrderManageService customerOrderManageService;

    /**
     * @Title: usermanageIndex
     * @Description:预约单列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author: wff
     * @return: ModelAndView
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public ModelAndView customerOrderlist(HttpServletRequest request,
            HttpServletResponse response, CustomerOrderVo covo)
            throws Exception {
        Page<CustomerOrderVo> page = this.findPageFinal(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyId", this.getCompanyId());
        params.put("companyName", this.getCompanyName());
        page.setParams(params);
        customerOrderManageService.findAllCustomerOrderByCompanyIdPaged(page);
        ModelAndView mv = createMAV("authmanage/usermanage/user_manage", page);
        return mv;
    }
}
