package com.jiezhu.pms.comm.page.impl;

import com.jiezhu.pms.comm.page.Pageable;

/**
 * @Description 分页实现-响应
 * @author
 * @date 2012-11-15
 * @version 1.0
 */
public class PageResponse implements Pageable {

    public PageResponse(Object result, Integer count) {
        this.result = result;
        this.count = count;
    }

    @Override
    public Integer getPageStart() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer getPageLimit() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public <RESULTTYPE> RESULTTYPE getResult(Class<RESULTTYPE> resultType) {
        return resultType.cast(result);
    }

    // 结果集
    private Object result;
    // 结果集总数
    private Integer count;
}
