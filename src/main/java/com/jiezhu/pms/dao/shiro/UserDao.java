package com.jiezhu.pms.dao.shiro;

import java.util.List;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.entity.Department;
import com.jiezhu.pms.entity.Employee;
import com.jiezhu.pms.entity.Employee2role;
import com.jiezhu.pms.entity.UserOperationRecords;
import com.jiezhu.pms.entity.vo.RealmVo;
import com.jiezhu.pms.entity.vo.UserManageVo;

public interface UserDao {

    void deleteByRoleId(Integer roleId);

    /**
     * 查找所有部门
     *
     * @author wff
     * @return
     */
    List<Department> findAllDept(Integer companyId);

    List<UserManageVo> findAllEmpByCompanyId(Page<UserManageVo> page)
            throws Exception;

    RealmVo getByUserNameAndCompany(RealmVo employee);

    /**
     * 新建用户
     *
     * @author wff
     * @param employee
     */
    long saveEmp(Employee employee) throws Exception;

    /**
     * 用户角色入库
     *
     * @author wff
     * @param er
     * @throws Exception
     */
    void saveEmployee2role(Employee2role er) throws Exception;

    /**
     * 根据公司id 用户id 查找用户
     *
     * @author wff
     * @param empId
     * @param companyId
     * @return
     */
    UserManageVo findEmployeeByEmpIdAndComId(Integer empId, Integer companyId);

    void updateEmp(Employee emp) throws Exception;

    void updateEmployee2role(Employee2role er) throws Exception;

    /**
     * 删除用户4 employee
     *
     * @author wff
     * @param empId
     */
    void deleteEmp(Integer empId);

    /**
     * 删除用户3employee2role
     *
     * @author wff
     * @param empId
     */
    void deleteEmp2role(Integer empId);

    /**
     * 删除用户2ChannelBill
     *
     * @author wff
     * @param empId
     */
    void deleteChannelBill(Integer empId);

    /**
     * 删除用户1
     *
     * @author wff
     * @param empId
     */
    void deleteChannel(Integer empId);

    /**
     *
     * @Title: updateEmpPass
     * @Description: 密码重置
     * @param empId
     * @throws Exception
     * @author: wff
     * @return: void
     */
    void updateEmpPass(Employee employee) throws Exception;

    /**
     * @Title: findEmployeeByEmpId
     * @Description: 根据主键查找Employee
     * @param empId
     * @return
     * @throws Exception
     * @author: wff
     * @return: Employee
     */
    Employee findEmployeeByEmpId(Integer empId) throws Exception;

    /**
     * @Title: saveUserOptRecords
     * @Description: 保存操作记录
     * @param uor
     * @author: wff
     * @return: void
     */
    void saveUserOptRecords(UserOperationRecords uor) throws Exception;

    /**
     * @Title: findUserOptListByEmpId
     * @Description: 查找用户操作记录列表
     * @param empId
     * @return
     * @throws Exception
     * @author: wff
     * @return: List<UserOperationRecords>
     */
    List<UserOperationRecords> findUserOptListByEmpId(Integer empId)
            throws Exception;

    /**
     * @Title: updateEmpPass
     * @Description: 修改密码
     * @param userId
     * @param password
     * @author: wff
     * @return: void
     */
    void updateEmpPassByUserId(Integer userId, String password);

    boolean findEmployeeByUserId(Integer userId, String newPassword)
            throws Exception;

}
