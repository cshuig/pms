package com.jiezhu.pms.dao.shiro;

import java.util.List;

import com.jiezhu.pms.entity.RoleOperationRecords;

public interface RecordDao {
    void del(Integer roleId);

    List<RoleOperationRecords> getRecordsByRoleId(Integer roleId);

    void recordRoleOperation(RoleOperationRecords record);
}
