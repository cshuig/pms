package com.jiezhu.pms.web.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.jiezhu.pms.service.shiro.RealmService;

public class ShiroDbFilterFactoryBean extends ShiroFilterFactoryBean implements
        InitializingBean {

    private static transient final Logger log = LoggerFactory
            .getLogger(ShiroDbFilterFactoryBean.class);

    // 追加到filterChainDefinition中的匹配定义
    private Map<String, String> appendChainDefinition;
    private RealmService realmService;

    public ShiroDbFilterFactoryBean() {
        super();
        appendChainDefinition = new LinkedHashMap<String, String>();
    }

    /*
     * 1、定义资源映射关系
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * @return the appendChainDefinition
     */
    public Map<String, String> getAppendChainDefinition() {
        return appendChainDefinition;
    }

    public RealmService getRealmService() {
        return realmService;
    }

    /**
     * @param appendChainDefinition
     *            the appendChainDefinition to set
     */
    public void setAppendChainDefinition(
            Map<String, String> appendChainDefinition) {
        this.appendChainDefinition = appendChainDefinition;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean#
     * setFilterChainDefinitionMap(java.util.Map)
     */
    @Override
    public void setFilterChainDefinitionMap(
            Map<String, String> filterChainDefinitionMap) {
        filterChainDefinitionMap.putAll(appendChainDefinition);
        filterChainDefinitionMap.putAll(realmService.findResourceMap());
        super.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    @Override
    public void setFilterChainDefinitions(String definitions) {
        throw new UnsupportedOperationException();
    }

    public void setRealmService(RealmService realmService) {
        this.realmService = realmService;

    }

}
