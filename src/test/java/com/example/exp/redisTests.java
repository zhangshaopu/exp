package com.example.exp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description: redisTests
 * @Author: zsp
 * @Date: 2023/6/23 19:18
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class redisTests {
    //默认key-value的序列化器是JdkSerializationRedisSerializer
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testSetStr(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set("str", "张三丰");
    }

    @Test
    public void testGetStr() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        String str = (String) redisTemplate.opsForValue().get("str");
        System.out.println(str);
    }

}
