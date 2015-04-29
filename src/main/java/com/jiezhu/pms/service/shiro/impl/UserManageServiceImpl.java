package com.jiezhu.pms.service.shiro.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.comm.util.ConfigUtil;
import com.jiezhu.pms.comm.util.DateUtil;
import com.jiezhu.pms.comm.util.EmailUtil;
import com.jiezhu.pms.comm.util.ShiroUtil;
import com.jiezhu.pms.dao.shiro.RoleDao;
import com.jiezhu.pms.dao.shiro.UserDao;
import com.jiezhu.pms.entity.Department;
import com.jiezhu.pms.entity.Employee;
import com.jiezhu.pms.entity.Employee2role;
import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.UserOperationRecords;
import com.jiezhu.pms.entity.vo.UserManageVo;
import com.jiezhu.pms.service.shiro.UserManageService;

@Log4j
@Transactional
@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public void saveOrUpdateEmp(UserManageVo umv) throws Exception {
        Employee emp = new Employee();
        BeanUtils.copyProperties(umv, emp);
        emp.setAccountname(umv.getUserName());
        emp.setLastSigninTime(new Date());
        emp.setPassword("000000");
        emp.setFlag(1);
        emp.setId(umv.getUserId());
        if (umv.getUserId() == null) {// 新增
            userDao.saveEmp(emp);
            Employee2role er = new Employee2role();
            er.setEmployeeId(emp.getId());
            er.setRoleId(umv.getRoleId());
            userDao.saveEmployee2role(er);
            this.sendEmailToEmplyeeService(emp.getAccountname(),
                    emp.getPassword(), emp.getEmail());
            this.saveUserOptRecords(emp, "add");
        } else {
            userDao.updateEmp(emp);
            Employee2role er = new Employee2role();
            er.setEmployeeId(emp.getId());
            er.setRoleId(umv.getRoleId());
            userDao.updateEmployee2role(er);
            this.saveUserOptRecords(emp, "update");
            ShiroUtil.reSetAuthorization(emp.getAccountname());
        }
    }

    @Override
    public List<Department> findAllDept(Integer companyId) {
        return userDao.findAllDept(companyId);
    }

    @Override
    public List<Role> findAllRoleByCompanyId(Integer comId) {
        return roleDao.findAllRoleByCompanyId(comId);
    }

    @Override
    public List<UserManageVo> findAllEmpByCompanyId(Page<UserManageVo> page)
            throws Exception {
        List<UserManageVo> umvList = userDao.findAllEmpByCompanyId(page);
        for (UserManageVo umv : umvList) {
            List<Role> roleList = this.findAllRoleByEmployeeId(umv.getUserId());
            umv.setRoleList(roleList);
            if (roleList.size() > 0) {
                umv.setRoleName(roleList.get(0).getName());
            }
        }
        return umvList;
    }

    @Override
    public List<Role> findAllRoleByEmployeeId(Integer empId) throws Exception {
        return roleDao.findAllRoleByEmployeeId(empId);
    }

    @Override
    public void findAllEmpByCompanyIdPaged(Page<UserManageVo> page)
            throws Exception {
        List<UserManageVo> umvList = this.findAllEmpByCompanyId(page);
        page.setResults(umvList);
    }

    @Override
    public UserManageVo findEmployeeByEmpId(Integer empId, Integer companyId)
            throws Exception {
        UserManageVo user = userDao.findEmployeeByEmpIdAndComId(empId,
                companyId);
        return user;
    }

    @Override
    public void deleteEmp(Integer empId) throws Exception {
        userDao.deleteEmp(empId);
    }

    @Override
    public boolean sendEmailToEmplyeeService(String accountname,
            String password, String email) throws Exception {
        String subject;
        String message;
        String[] emails = { email };
        try {
            subject = ConfigUtil.getConfig("mail.template.employee.subject");
            message = ConfigUtil.getConfig("mail.template.employee.message");
        } catch (Exception e) {
            log.error("获取邮件主题、信息模板信息失败！");
            return false;
        }

        try {
            EmailUtil.sendHtmlEmail(
                    MessageFormat.format(subject, accountname, password),
                    MessageFormat.format(message, accountname, password),
                    emails);
        } catch (EmailException e) {
            log.error("发送邮件失败！", e);
            return false;
        }

        return true;
    }

    @Override
    public void passReset(Integer empId) throws Exception {
        Employee emp = userDao.findEmployeeByEmpId(empId);
        emp.setPassword("000000");
        userDao.updateEmpPass(emp);
        this.sendEmailToEmplyeeService(emp.getAccountname(), emp.getPassword(),
                emp.getEmail());
    }

    public void saveUserOptRecords(Employee emp, String flag) throws Exception {
        StringBuffer desc = new StringBuffer();
        desc.append(ShiroUtil.getShiroUser().getUserName());
        desc.append("(" + DateUtil.getCurrentTimeStamp() + ")");
        if ("add".equals(flag)) {
            desc.append("新增了用户信息:" + emp.getName());
        } else {
            desc.append("更新了用户信息:" + emp.getName());
        }
        Integer userId = ShiroUtil.getShiroUser().getUserId();
        UserOperationRecords uor = new UserOperationRecords(emp.getId(),
                userId, new Date(), desc.toString());
        userDao.saveUserOptRecords(uor);
    }

    @Override
    public List<UserOperationRecords> findUserOptListByEmpId(Integer empId)
            throws Exception {
        return userDao.findUserOptListByEmpId(empId);
    }

    @Override
    public void updatePassword(UserManageVo umv) throws Exception {
        userDao.updateEmpPassByUserId(umv.getUserId(), umv.getNewPassword());
    }

    @Override
    public boolean isOldPasswd(UserManageVo umv) throws Exception {
        return userDao.findEmployeeByUserId(umv.getUserId(),
                umv.getOldPassword());
    };
}
