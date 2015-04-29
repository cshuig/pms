package com.jiezhu.pms.comm.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CollectionUtil {
    public static String getIncrease(String before, String after) {
        Set be = stringToSet(before.split(","));
        Set af = stringToSet(after.split(","));
        Set set = merge(be, af);
        String rs = set.toString();
        rs = rs.substring(1, rs.length() - 1);
        return rs;
    }

    public static String getReduce(String before, String after) {
        return getIncrease(after, before);
    }

    private static Set merge(Set before, Set after) {
        Set set = new HashSet();
        set.addAll(before);
        set.addAll(after);
        set.retainAll(after);
        set.removeAll(before);
        return set;
    }

    public static Set stringToSet(String[] temp) {
        Set set = new HashSet();
        set.addAll(Arrays.asList(temp));
        return set;
    }

}
