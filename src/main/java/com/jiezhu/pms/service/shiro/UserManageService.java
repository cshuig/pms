package com.jiezhu.pms.service.shiro;

import java.util.List;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.entity.Department;
import com.jiezhu.pms.entity.Role;
import com.jiezhu.pms.entity.UserOperationRecords;
import com.jiezhu.pms.entity.vo.UserManageVo;

/**
 * @ClassName: UserManageService
 * @Description: TODO
 * @author: wff
 * @date: 2014年12月25日 下午2:33:31
 */
public interface UserManageService {
    /**
     * 保存或更新用户
     *
     * @author wff
     * @param employee
     * @throws Exception
     */
    public void saveOrUpdateEmp(UserManageVo umv) throws Exception;

    /**
     * 查找所有的部门
     *
     * @author wff
     * @return
     */
    List<Department> findAllDept(Integer companyId);

    /**
     * 查找某公司下所有角色
     *
     * @author wff
     * @return
     */
    List<Role> findAllRoleByCompanyId(Integer comId);

    /**
     * 查找某公司下所有员工
     *
     * @author wff
     * @param comId
     * @return
     */
    public List<UserManageVo> findAllEmpByCompanyId(Page<UserManageVo> page)
            throws Exception;

    /**
     * 查找某员工的所有角色
     *
     * @author wff
     * @param empId
     * @throws Exception
     */
    public List<Role> findAllRoleByEmployeeId(Integer empId) throws Exception;

    /**
     * 某公司员工分页列表
     *
     * @author wff
     * @param page
     * @param comId
     */
    public void findAllEmpByCompanyIdPaged(Page<UserManageVo> page)
            throws Exception;

    /**
     * 用户查看
     *
     * @author wff
     * @param umv
     * @return
     */
    public UserManageVo findEmployeeByEmpId(Integer empId, Integer companyId)
            throws Exception;

    /**
     * 删除用户 先删子表employee2role 再删父表employee
     *
     * @author wff
     * @param empId
     * @throws Exception
     */
    public void deleteEmp(Integer empId) throws Exception;

    public boolean sendEmailToEmplyeeService(String accountname,
            String password, String email) throws Exception;

    /**
     * @Title: passReset
     * @Description: 密码重置
     * @param empId
     * @throws Exception
     * @author: wff
     * @return: void
     */
    public void passReset(Integer empId) throws Exception;

    /**
     * @Title: findUserOptListByEmpId
     * @Description:查找用户操作记录列表
     * @param empId
     * @return
     * @throws Exception
     * @author: wff
     * @return: List<UserOperationRecords>
     */
    public List<UserOperationRecords> findUserOptListByEmpId(Integer empId)
            throws Exception;

    /**
     * @Title: updatePassword
     * @Description: 密码修改
     * @param umv
     * @throws Exception
     * @author: wff
     * @return: void
     */
    public void updatePassword(UserManageVo umv) throws Exception;

    /**
     * @Title: isOldPasswd
     * @Description: 旧密码是否正确
     * @param umv
     * @return
     * @throws Exception
     * @author: wff
     * @return: boolean
     */
    public boolean isOldPasswd(UserManageVo umv) throws Exception;
}
