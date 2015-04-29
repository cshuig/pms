package com.jiezhu.pms.dao.comm;

import java.util.List;
import java.util.Map;

import com.jiezhu.pms.entity.vo.Combox;

/**
 * @ClassName: ComboBoxDAO
 * @Description: 下拉列表初始化
 * @author: 张波波
 * @date: 2014年12月25日 下午3:27:05
 */
public interface ComboBoxDAO {

    /**
     * @Description: 下拉框列表初始化
     * @return: List
     * @Author: 张波波
     * @date: 2014 2014年12月25日
     */
    public List<Combox> searchComboxList(Map<String, Object> params);
}
