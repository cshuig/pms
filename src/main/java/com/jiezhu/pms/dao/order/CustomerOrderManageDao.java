package com.jiezhu.pms.dao.order;

import java.util.List;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.entity.vo.CustomerOrderVo;

public interface CustomerOrderManageDao {
    /**
     * @Title: findAllCustomerOrderByCompanyIdPaged
     * @Description: 查找预约单分页
     * @param page
     * @return
     * @throws Exception
     * @author: wff
     * @return: List<CustomerOrderVo>
     */
    List<CustomerOrderVo> findAllCustomerOrderByCompanyIdPaged(
            Page<CustomerOrderVo> page) throws Exception;

}
