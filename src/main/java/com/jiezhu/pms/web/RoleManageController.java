package com.jiezhu.pms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.jiezhu.pms.comm.util.CollectionUtil;
import com.jiezhu.pms.comm.util.DateUtil;
import com.jiezhu.pms.comm.util.ShiroUtil;
import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.RoleOperationRecords;
import com.jiezhu.pms.entity.vo.Menu;
import com.jiezhu.pms.entity.vo.RoleManageVo;
import com.jiezhu.pms.service.comm.RecordService;
import com.jiezhu.pms.service.shiro.RoleManageService;

@Controller
@RequestMapping("/role")
public class RoleManageController extends MultiActionController {
    @Autowired
    private RoleManageService roleManageService;

    @Autowired
    private RecordService recordService;

    private final String ADD_RECORD = "%s(%s)创建了角色:%s";
    private final String UPDATE_RECORD = "%s(%s)修改了角色:%s的权限";
    private final String DELETE_RECORD = "%s(%s)删除了角色:%s";
    private final String ADD_PRIVILEGE = ",授与权限:%s";
    private final String DELETE_PRIVILEGE = ",收回权限:%s";
    private final String SUCCESS = "1";

    @RequestMapping(value = "/add", method = { RequestMethod.POST })
    @ResponseBody
    public String addRole(RoleManageVo rVo) {
        Integer companyId;
        String result = "[]";
        String userName = "";
        try {
            companyId = ShiroUtil.getCompanyId();
            userName = ShiroUtil.getShiroUser().getUserName();
        } catch (Exception e) {
            return result;
        }
        rVo.setCompanyId(companyId);
        Role role = roleManageService.addRole(rVo);
        Integer roleId = role.getId();

        if (roleId != null) {

            String now = DateUtil.getCurrentTimeStamp();
            String privileges = roleManageService.getPrivilegeNames(rVo
                    .getPrivilegeIds().split(","));
            Object args[] = { userName, now, role.getName(), privileges };
            String desc = String.format(ADD_RECORD + ADD_PRIVILEGE, args);
            record(roleId, desc);
        }
        result = String.format("{\"result\":\"%s\"}", roleId);
        return result;
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.DELETE })
    // DELETE
    @ResponseBody
    public String deleteRole(Integer roleId) {
        String result = "[]";
        Role role = roleManageService.deleteByRoleId(roleId);
        if (role != null) {
            result = String.format("{\"result\":\"%s\"}", roleId);
            String userName = "";
            try {
                userName = ShiroUtil.getShiroUser().getUserName();
            } catch (Exception e) {
                return result;
            }
            String now = DateUtil.getCurrentTimeStamp();
            Object args[] = { userName, now, role.getName() };
            String desc = String.format(DELETE_RECORD, args);
            record(roleId, desc);
        }

        return result;
    }

    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("authmanage/rolemanage/role_manage");
        Integer companyId = null;
        try {
            companyId = ShiroUtil.getCompanyId();
        } catch (Exception e) {
            mv = new ModelAndView("login");
            return mv;
        }
        List<RoleManageVo> list = roleManageService
                .getAllRoleAndPrivilege(companyId);
        mv.addObject("dataList", list);
        return mv;
    }

    private String merge(RoleManageVo rVo) {
        Object[] args = new Object[5];
        String userName = ShiroUtil.getShiroUser().getUserName();//
        args[0] = userName;
        args[1] = DateUtil.getCurrentTimeStamp();
        args[2] = rVo.getRoleName();

        String newids = rVo.getPrivilegeIds();
        String oldids = roleManageService
                .getPrivilegeIdsByRole(rVo.getRoleId());
        String addids = CollectionUtil.getIncrease(oldids, newids);
        String delids = CollectionUtil.getReduce(oldids, newids);

        String f = UPDATE_RECORD;
        if (!"".equals(addids.trim())) {
            f += ADD_PRIVILEGE;
            args[3] = roleManageService.getPrivilegeNames(addids.split(","));
        } else {
            args[3] = "";
        }

        if (!"".equals(delids.trim())) {
            f += DELETE_PRIVILEGE;
            args[4] = roleManageService.getPrivilegeNames(delids.split(","));
        } else {
            args[4] = "";
        }

        return String.format(f, args);
    }

    @RequestMapping(value = "/add", method = { RequestMethod.GET })
    public ModelAndView privilegeList() {
        ModelAndView mv = new ModelAndView("authmanage/rolemanage/role_add");
        List<Menu> dataList = roleManageService.getAllPrivileges();
        mv.addObject("dataList", dataList);
        return mv;
    }

    public void record(Integer roleId, String desc) {
        Integer operatorId = 1;// ShiroUtil.getUserId();
        RoleOperationRecords record = new RoleOperationRecords();
        record.setRoleId(roleId);
        record.setDescription(desc);
        record.setOperatorId(operatorId);
        recordService.addRoleOperateion(record);
    }

    @RequestMapping(value = "/show", method = { RequestMethod.GET })
    public ModelAndView role(Integer roleId) {
        ModelAndView mv = new ModelAndView("authmanage/rolemanage/role_show");
        List<Menu> dataList = roleManageService.getResourcesByRoleId(roleId);
        List<RoleOperationRecords> records = recordService
                .getRoleOperationRecords(roleId);
        Role role = roleManageService.getRole(roleId);
        mv.addObject("dataList", dataList);
        mv.addObject("records", records);
        mv.addObject("roleName", role.getName());
        return mv;
    }

    @RequestMapping(value = "/update", method = { RequestMethod.GET })
    public ModelAndView rolePrivilegeList(Integer roleId) {
        ModelAndView mv = new ModelAndView("authmanage/rolemanage/role_update");
        List<Menu> dataList = roleManageService.getResourcesByRoleId(roleId);
        Role role = roleManageService.getRole(roleId);
        mv.addObject("dataList", dataList);
        mv.addObject("roleName", role.getName());
        mv.addObject("roleId", roleId);
        return mv;
    }

    private String success() {
        return String.format("{\"result\":\"%s\"}", SUCCESS);
    }

    /**
     * put 无json
     *
     * @Title: updateRole
     * @Description: TODO
     * @param rVo
     * @return
     * @return: ModelAndView
     */
    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    @ResponseBody
    public String updateRole(RoleManageVo rVo) {
        String result = "[]";
        String desc = merge(rVo).replaceAll(" ", "");
        Role role = roleManageService.updateRole(rVo);
        if (role != null) {
            Integer roleId = rVo.getRoleId();
            record(roleId, desc);
            result = success();
        }
        return result;
    }

}
