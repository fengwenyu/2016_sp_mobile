package com.shanpin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:cache-spring.xml")
public class SpringTest {

	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void run(){
		
		ValueOperations opsForValue = redisTemplate.opsForValue();
		
		opsForValue.set("上帝", "123456789");
		
		Object object = opsForValue.get("yanxiaobin");
		
		System.out.println(object);
		
	}
}
