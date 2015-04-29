package com.jiezhu.com;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.jiezhu.pms.comm.util.CollectionUtil;
import com.jiezhu.pms.entity.Resources;
import com.jiezhu.pms.entity.vo.RoleManageVo;

public class HomeTest {

    @Test
    public void fatherId() {
        String ids = "2,222,211,212,1";
        RoleManageVo vo = new RoleManageVo();
        vo.setPrivilegeIds(ids);
        vo.formatPrivileges();
        System.out.println(vo.getPrivilegeIds());
    }

    @Test
    public void keys() throws Exception {

        String url = "http://jiezhu.pms.woniu.com";
        // url = "http://localhost/";
        String company = Resources.getCompany(url);
        assertEquals(company, "jiezhu");

    }

    private Set merge(Set before, Set after) {
        Set set = new HashSet();
        set.addAll(before);
        set.addAll(after);
        set.retainAll(after);
        set.removeAll(before);
        return set;
    }

    @Test
    public void set() {
        String before = "1,2,3";

        String after = "4,2,3,6";

        System.out.println(CollectionUtil.getIncrease(before, after));
        System.out.println(CollectionUtil.getReduce(before, after));
    }

    @Test
    public void string() throws Exception {

        String url = "%s(%s)";
        Object args[] = { "role", new Date() };
        String company = String.format(url, args);
        System.out.println(company);

    }

}
