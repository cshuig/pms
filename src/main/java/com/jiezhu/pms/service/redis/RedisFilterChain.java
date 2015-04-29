package com.jiezhu.pms.service.redis;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import com.jiezhu.pms.comm.util.JedisUtil;
import com.jiezhu.pms.entity.vo.RedisCache;

public class RedisFilterChain implements MessageListener {

    public static void rebuild() {
        JedisUtil.pub(CHANNEL, FILTER);
    }

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    public static String CHANNEL = "filterChain";

    public static String FILTER = "filterChain";

    public static String AUTHORIZATION = "Authorization";

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = message.toString();
        if (msg.indexOf(FILTER) != -1) {
            reBuildFilterChains();
        } else if (msg.indexOf(AUTHORIZATION) != -1) {// test
            String key = RedisCache.keyPrefix + "*";
            JedisUtil.clear(key);
        }
    }

    /**
     *
     */
    public synchronized void reBuildFilterChains() {

        AbstractShiroFilter shiroFilter = null;
        try {
            shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                    .getObject();
        } catch (Exception e) {
            throw new RuntimeException(
                    "get ShiroFilter from shiroFilterFactoryBean error!");
        }

        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                .getFilterChainResolver();
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                .getFilterChainManager();

        // 清空老的权限控制
        manager.getFilterChains().clear();

        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(new HashMap());
        // 重新构建生成
        Map<String, String> chains = shiroFilterFactoryBean
                .getFilterChainDefinitionMap();
        for (Map.Entry<String, String> entry : chains.entrySet()) {
            String url = entry.getKey();
            String chainDefinition = entry.getValue().replace(" ", "");
            manager.createChain(url, chainDefinition);
        }

    }

}
