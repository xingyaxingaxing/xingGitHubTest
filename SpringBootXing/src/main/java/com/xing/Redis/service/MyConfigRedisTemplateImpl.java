package com.xing.Redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyConfigRedisTemplateImpl implements MyConfigRedisTemplate {
    @Autowired
    private RedisTemplate redisTemplate; //在MyRedisConfig文件中配置了redisTemplate的序列化之后， 客户端也能正确显示键值对了

    public Map<String, Object> qwe() {
        redisTemplate.opsForValue().set("wujinxing", "lige");
        System.out.println(redisTemplate.opsForValue().get("wujinxing"));
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName(String.format("测试%d", i));
            user.setAge(i + 10);
            map.put(String.valueOf(i), user);
        }
        redisTemplate.opsForHash().putAll("测试", map);
        BoundHashOperations hashOps = redisTemplate.boundHashOps("测试");
        Map map1 = hashOps.entries();
        return map1;
    }

    static class User implements Serializable {
        private int id;
        private String name;
        private long age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getAge() {
            return age;
        }

        public void setAge(long age) {
            this.age = age;
        }
    }
}