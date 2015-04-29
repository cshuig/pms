package com.jiezhu.com;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jiezhu.pms.entity.RoleOperationRecords;
import com.jiezhu.pms.service.comm.RecordService;

/**
 * @Description:
 *
 * @author dell
 * @version TODO
 */
@ContextConfiguration(locations = { "classpath:/spring-basic.xml" })
@WebAppConfiguration
public class RecordTestcase extends AbstractJUnit4SpringContextTests {

    @Autowired
    private RecordService recordService;

    @Test
    public void recordRole() {
        Integer roleId = 1;
        RoleOperationRecords record = new RoleOperationRecords();
        record.setOperatorId(1);
        record.setRoleId(roleId);
        record.setDescription("g");
        recordService.addRoleOperateion(record);

        List<RoleOperationRecords> list = recordService
                .getRoleOperationRecords(roleId);
        assertNotNull(list);
    }

}
