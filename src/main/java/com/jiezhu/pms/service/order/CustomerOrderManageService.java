package com.jiezhu.pms.service.order;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.entity.vo.CustomerOrderVo;

public interface CustomerOrderManageService {
    /**
     * @Title: findAllCustomerOrderByCompanyIdPaged
     * @Description: 查找所有的预约单分页
     * @param page
     * @throws Exception
     * @author: wff
     * @return: void
     */
    Page<CustomerOrderVo> findAllCustomerOrderByCompanyIdPaged(
            Page<CustomerOrderVo> page) throws Exception;

}
