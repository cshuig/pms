package com.jiezhu.pms.comm.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jiezhu.pms.entity.vo.RedisCache;
import com.jiezhu.pms.entity.vo.ShiroUser;
import com.jiezhu.pms.service.redis.RedisFilterChain;
import com.jiezhu.pms.web.shiro.ShiroDbRealm;

public class ShiroUtil {
    public static Integer getCompanyId() {
        return getShiroUser().getCompanyId();
    }

    public static String getSessionId() {
        Subject subject = SecurityUtils.getSubject();
        String sid = subject.getSession().getId().toString();
        return sid;
    }

    public static ShiroUser getShiroUser() {
        return (ShiroUser) getSubject().getPrincipal();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Integer getUserId() {
        return getShiroUser().getUserId();
    }

    public static void rebuildFilterChain() {
        JedisUtil.pub(RedisFilterChain.CHANNEL, RedisFilterChain.FILTER);
    }

    public static void reSetAuthorization() {
        // JedisUtil.pub(RedisFilterChain.CHANNEL,
        // RedisFilterChain.AUTHORIZATION);
        String key = RedisCache.keyPrefix + "*";
        JedisUtil.clear(key);
    }

    public static void reSetAuthorization(String userName) {
        ShiroUser sUser = new ShiroUser();
        sUser.setCompany(ShiroUtil.getShiroUser().getCompany());
        sUser.setUserName(userName);
        String key = String.format("%s%s.authorizationCache:%s",
                RedisCache.keyPrefix, ShiroDbRealm.class.getName(), sUser);
        JedisUtil.del(key);
    }

    public Subject getSubject(String sessionId) {
        return new Subject.Builder().sessionId(sessionId).buildSubject();
    }
}
