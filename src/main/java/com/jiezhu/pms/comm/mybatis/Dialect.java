package com.jiezhu.pms.comm.mybatis;

public abstract class Dialect {

    public static enum Type {
        MYSQL, POSTGRESQL
    }

    public abstract String getLimitString(String sql, int offset, int limit);
}
