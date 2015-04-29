package com.jiezhu.pms.service.comm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiezhu.pms.dao.shiro.RecordDao;
import com.jiezhu.pms.entity.RoleOperationRecords;
import com.jiezhu.pms.service.comm.RecordService;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordDao recordDao;

    @Override
    public void addRoleOperateion(RoleOperationRecords record) {
        recordDao.recordRoleOperation(record);
    }

    @Override
    public List<RoleOperationRecords> getRoleOperationRecords(Integer roleId) {
        return recordDao.getRecordsByRoleId(roleId);
    }

}
