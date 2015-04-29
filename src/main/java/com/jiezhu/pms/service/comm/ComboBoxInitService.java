package com.jiezhu.pms.service.comm;

import java.util.List;

import com.jiezhu.pms.entity.vo.Combox;

/**
 * @ClassName: SelectBoxInitService
 * @Description: 初始化下拉列表
 * @author: 张波波
 * @date: 2014年12月25日 下午2:31:19
 */
public interface ComboBoxInitService {
    
    /**
     * @Description: 下拉列表初始化
     * @return: 下拉列表值
     * @Author: 张波波
     * @date: 2014 2014年12月25日
     */
    List<Combox> searchComboxList(String comboxEnum);
}
