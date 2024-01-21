package com.bill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class TestApp2 {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() {

        ValueOperations<String, String> op = stringRedisTemplate.opsForValue();
        op.set("name", "Bill");
        System.out.println(op.get("name"));

    }

}
