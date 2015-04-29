package com.jiezhu.pms.service.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.dao.order.CustomerOrderManageDao;
import com.jiezhu.pms.entity.vo.CustomerOrderVo;
import com.jiezhu.pms.service.order.CustomerOrderManageService;

@Service
public class CustomerOrderManageServiceImpl implements
        CustomerOrderManageService {
    @Autowired
    private CustomerOrderManageDao customerOrderManageDao;

    @Override
    public Page<CustomerOrderVo> findAllCustomerOrderByCompanyIdPaged(
            Page<CustomerOrderVo> page) throws Exception {
        List<CustomerOrderVo> coList = customerOrderManageDao
                .findAllCustomerOrderByCompanyIdPaged(page);
        page.setResults(coList);
        return page;
    }

}
