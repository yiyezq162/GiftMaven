package com.example.gifthavenbackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.Set;


@SpringBootTest
class GiftHavenBackendApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("test2","成功了真开心");
		System.out.println(redisTemplate.opsForValue().get("test2"));
	}

	@Test
	public void demo1() {
		Jedis jedis = new Jedis("www.meet0208.icu", 26379);
		jedis.auth("admin@111");

		jedis.set("name", "John");
		String name = jedis.get("name");
		System.out.println(name);

		Set<String> keys = jedis.keys("*");
		for (String s : keys) {
			System.out.println(s);
		}

	}

}
