package org.redis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import redis.clients.jedis.Jedis;

import com.jiezhu.pms.entity.vo.RedisCache;
import com.jiezhu.pms.entity.vo.ShiroUser;
import com.jiezhu.pms.web.shiro.ShiroDbRealm;

@ContextConfiguration(locations = { "classpath:/spring-basic.xml" })
@WebAppConfiguration
public class JedisTestcase extends AbstractJUnit4SpringContextTests {

    @Autowired
    private RedisTemplate<String, ShiroUser> redisTemplate;

    @Test
    public void clear() {
        ShiroUser sUser = new ShiroUser();
        sUser.setCompany("jiezhu");
        sUser.setUserName("张飞");

        String key = String.format("%s%s.authorizationCache:%s",
                RedisCache.keyPrefix, ShiroDbRealm.class.getName(), sUser);
        Object obj = redisTemplate.opsForValue().get(key);
        System.out.println(obj);
        redisTemplate.delete(key);
        obj = redisTemplate.opsForValue().get(key);
        System.out.println(obj);
    }

    @Test
    public void jedis() throws Exception {
        Jedis jedis = new Jedis("123.57.12.88");
        String key = "name";
        String value = "jedis";
        // jedis.del(key);
        jedis.set(key, value);
        String jval = jedis.get(key);
        assertEquals(jval, value);
    }

    @Test
    public void keys() throws Exception {

        Set<String> set = redisTemplate.keys("*");
        assertNotNull(set);

    }

    @Test
    public void springredis() throws Exception {
        String key = "shiroUser";
        ShiroUser sUser = new ShiroUser();
        sUser.setSessionId("1");
        redisTemplate.opsForValue().set(key, sUser);
        System.out.println(key.getBytes());
        ShiroUser jsval = redisTemplate.opsForValue().get(key);
        assertEquals(jsval.getSessionId(), sUser.getSessionId());
    }

}
