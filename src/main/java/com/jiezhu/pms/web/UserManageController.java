package com.jiezhu.pms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.comm.util.Constants;
import com.jiezhu.pms.entity.Department;
import com.jiezhu.pms.entity.Employee;
import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.UserOperationRecords;
import com.jiezhu.pms.entity.vo.UserManageVo;
import com.jiezhu.pms.service.shiro.UserManageService;
import com.jiezhu.pms.web.comm._BaseController;

@Controller
@RequestMapping("/account")
public class UserManageController extends _BaseController {
    @Autowired
    private UserManageService userManageService;

    /**
     * 用户管理首页
     *
     * @author wff
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public ModelAndView usermanageIndex(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Page<UserManageVo> page = this.findPageFinal(request);
        Map<String, Object> params = new HashMap<String, Object>();
        String userName = this.findStringParameterValue(request, "userName");
        String roleName = this.findStringParameterValue(request, "roleName");
        params.put("userName", userName);
        params.put("roleName", roleName);
        params.put("companyId", this.getCompanyId());
        page.setParams(params);
        userManageService.findAllEmpByCompanyIdPaged(page);
        ModelAndView mv = createMAV("authmanage/usermanage/user_manage", page);
        mv.addObject(Constants.FLAG,
                this.findStringParameterValue(request, Constants.FLAG));
        return mv;
    }

    @RequestMapping(value = "/list", method = { RequestMethod.POST })
    @ResponseBody
    public ModelMap usermanageIndexAjax(HttpServletRequest request,
            HttpServletResponse response, ModelAndView mv) throws Exception {
        Page<UserManageVo> page = this.findPageFinal(request);
        Map<String, Object> params = new HashMap<String, Object>();
        String userName = this.findStringParameterValue(request, "userName");
        String roleName = this.findStringParameterValue(request, "roleName");
        params.put("userName", userName);
        params.put("roleName", roleName);
        params.put("companyId", this.getCompanyId());
        page.setParams(params);
        userManageService.findAllEmpByCompanyIdPaged(page);
        ModelMap mm = new ModelMap();
        mm.addAttribute(page);
        return mm;
    }

    /**
     * 新建用户首页
     *
     * @author wff
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = { RequestMethod.GET })
    public ModelAndView userAddIndex(HttpServletRequest request, Employee emp,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("authmanage/usermanage/user_add");
        List<Department> deptList = userManageService.findAllDept(this
                .getCompanyId());
        List<Role> roleList = userManageService.findAllRoleByCompanyId(this
                .getCompanyId());
        mv.addObject("deptList", deptList);
        mv.addObject("roleList", roleList);
        mv.addObject(Constants.FLAG,
                this.findStringParameterValue(request, Constants.FLAG));
        return mv;
    }

    /**
     * 新建用户
     *
     * @author wff
     * @param request
     * @param emp
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = { RequestMethod.POST })
    public ModelAndView useradd(HttpServletRequest request, UserManageVo umv,
            HttpServletResponse response) throws Exception {
        umv.setCompanyId(this.getCompanyId());
        userManageService.saveOrUpdateEmp(umv);
        ModelAndView mv = new ModelAndView("redirect:/account/add");
        mv.addObject(Constants.FLAG, Constants.ADD);
        return mv;
    }

    /**
     * 用户查看
     *
     * @author wff
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/show", method = { RequestMethod.GET })
    public ModelAndView userinfo(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Integer empId = this.findIntegerParameterValue(request, "userId");
        UserManageVo userinfo = userManageService.findEmployeeByEmpId(empId,
                this.getCompanyId());
        List<Department> deptList = userManageService.findAllDept(this
                .getCompanyId());
        List<Role> roleList = userManageService.findAllRoleByCompanyId(this
                .getCompanyId());
        List<UserOperationRecords> userOptList = userManageService
                .findUserOptListByEmpId(empId);

        ModelAndView mv = new ModelAndView("authmanage/usermanage/userinfo");
        mv.addObject("userinfo", userinfo);
        mv.addObject("deptList", deptList);
        mv.addObject("roleList", roleList);
        mv.addObject("userOptList", userOptList);
        mv.addObject(Constants.FLAG, Constants.VIEW);
        return mv;
    }

    /**
     * 来到用户修改页
     *
     * @author wff
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = { RequestMethod.GET })
    public ModelAndView toUserUpdate(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Integer empId = this.findIntegerParameterValue(request, "userId");
        UserManageVo userinfo = userManageService.findEmployeeByEmpId(empId,
                this.getCompanyId());
        List<Department> deptList = userManageService.findAllDept(this
                .getCompanyId());
        List<Role> roleList = userManageService.findAllRoleByCompanyId(this
                .getCompanyId());
        List<UserOperationRecords> userOptList = userManageService
                .findUserOptListByEmpId(empId);
        ModelAndView mv = new ModelAndView("authmanage/usermanage/userinfo");
        mv.addObject("userinfo", userinfo);
        mv.addObject("deptList", deptList);
        mv.addObject("roleList", roleList);
        mv.addObject("userOptList", userOptList);
        mv.addObject(Constants.FLAG, Constants.UPDATE);
        return mv;
    }

    /**
     * 用户修改
     *
     * @author wff
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    public ModelAndView userUpdate(HttpServletRequest request,
            UserManageVo umv, HttpServletResponse response) throws Exception {
        umv.setCompanyId(this.getCompanyId());
        userManageService.saveOrUpdateEmp(umv);
        ModelAndView mv = new ModelAndView("redirect:/account/list");
        mv.addObject(Constants.FLAG, Constants.UPDATE);
        return mv;
    }

    /**
     * 用户删除
     *
     * @author wff
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = { RequestMethod.POST })
    @ResponseBody
    public ModelMap empDelete(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Integer empId = this.findIntegerParameterValue(request, "userId");
        userManageService.deleteEmp(empId);
        ModelMap mm = new ModelMap();
        mm.addAttribute("success", 1);
        return mm;
    }

    /**
     * @Title: passReset
     * @Description: 密码重置
     * @param request
     * @param response
     * @throws Exception
     * @author: wff
     * @return: void
     */
    @RequestMapping(value = "/resetPassword", method = { RequestMethod.POST })
    @ResponseBody
    public ModelMap passReset(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Integer empId = this.findIntegerParameterValue(request, "userId");
        userManageService.passReset(empId);
        ModelMap mm = new ModelMap();
        mm.addAttribute("success", 1);
        return mm;
    }

    /**
     * @Title: toUpdatePassword
     * @Description: 来到修改密码页
     * @param request
     * @param umv
     * @param response
     * @return
     * @throws Exception
     * @author: wff
     * @return: ModelAndView
     */
    @RequestMapping(value = "/updatePassword", method = { RequestMethod.GET })
    public ModelAndView toUpdatePassword(HttpServletRequest request,
            UserManageVo umv, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView(
                "authmanage/usermanage/update_passwd");
        mv.addObject(Constants.FLAG,
                this.findStringParameterValue(request, Constants.FLAG));
        return mv;
    }

    /**
     * @Title: updatePassword
     * @Description: 修改密码
     * @param request
     * @param umv
     * @param response
     * @return
     * @throws Exception
     * @author: wff
     * @return: ModelAndView
     */
    @RequestMapping(value = "/updatePassword", method = { RequestMethod.POST })
    public ModelAndView updatePassword(HttpServletRequest request,
            UserManageVo umv, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/account/updatePassword");
        umv.setUserId(this.getUserId());
        boolean isOldPasswd = userManageService.isOldPasswd(umv);
        if (isOldPasswd) {
            userManageService.updatePassword(umv);
            mv.addObject(Constants.FLAG, Constants.SUCCESS);
            return mv;
        } else {
            mv.addObject(Constants.FLAG, Constants.ERROR);
            return mv;
        }
    }
}
