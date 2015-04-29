package com.jiezhu.pms.comm.page;

/**
 * @Description 分页接口
 * @author
 * @date 2012-11-15
 * @version 1.0
 */
public interface Pageable {

    /**
     * @Description 获取起始页索引
     * @return
     * @author
     */
    Integer getPageStart();

    /**
     * @Description 获取单页显示总量
     * @return
     * @author
     */
    Integer getPageLimit();

    /**
     * @Description 获取结果总数
     * @return
     * @author
     */
    Integer getCount();

    /**
     * @Description 获取结果集
     * @param resultType
     *            结果集类型
     * @return
     * @author
     */
    <RESULTTYPE> RESULTTYPE getResult(Class<RESULTTYPE> resultType);

}
