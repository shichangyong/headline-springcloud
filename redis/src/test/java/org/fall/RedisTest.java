package org.fall;

import org.fall.service.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;

@SpringBootTest
public class RedisTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtils redisUtils;

    @Test
    void getValue(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String key = "hello"; // 以及存在redis中
        String value = operations.get(key);
        if (value != null){
            System.out.println(value);
        }else{
            System.out.println("不存在");
        }

    }

    @Test
    void getMapValue(){
        Object object = redisUtils.getMap("testMap");
        if(object != null){
            System.out.println(object.toString());
        }else{
            System.out.println("不存在");
        }
    }

    @Test
    void setMapValue(){
        HashMap<String, String> map = new HashMap<>();
        map.put("1","cnm");
        map.put("2","rnm");
        boolean result = false;
        try {
            HashOperations hashOperations = redisTemplate.opsForHash();
            hashOperations.putAll("testMap", map);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(result){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }
}
