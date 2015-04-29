package com.jiezhu.pms.service.comm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiezhu.pms.dao.comm.ComboBoxDAO;
import com.jiezhu.pms.entity.vo.Combox;
import com.jiezhu.pms.service.comm.ComboBoxInitService;

/**
 * @ClassName: ComboBoxInitServiceImpl
 * @Description: 初始化下拉列表
 * @author: 张波波
 * @date: 2014年12月25日
 */
@Transactional
@Service
public class ComboBoxInitServiceImpl implements ComboBoxInitService {

    @Autowired
    ComboBoxDAO comboBoxDao;
    /**
     * @Description: 下拉列表初始化
     * @return: 下拉列表值
     * @Author: 张波波
     * @date: 2014 2014年12月25日
     */
    @Override
    public List<Combox> searchComboxList(String comboxEnum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("enum", comboxEnum);
        return comboBoxDao.searchComboxList(params);
    }    
}
