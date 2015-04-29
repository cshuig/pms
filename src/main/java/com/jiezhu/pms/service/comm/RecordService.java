package com.jiezhu.pms.service.comm;

import java.util.List;

import com.jiezhu.pms.entity.RoleOperationRecords;

public interface RecordService {
    void addRoleOperateion(RoleOperationRecords record);

    List<RoleOperationRecords> getRoleOperationRecords(Integer roleId);

}
