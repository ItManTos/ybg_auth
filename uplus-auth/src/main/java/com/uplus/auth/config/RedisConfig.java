package com.uplus.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 *
 * @Description: redis缓存配置
 * @Author: huangcong
 * @Date: 2018/6/11 下午4:17
 *
 */
@Configuration
public class RedisConfig {

	private Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	@Bean

	public JedisPool jedisPool(JedisPoolConfig config, @Value("${spring.redis.host}") String host,
			@Value("${spring.redis.port}") int port, @Value("${spring.redis.password}") String password,
			@Value("${spring.redis.database}") int database) {
		logger.info("缓存服务器的地址：" + host + ":" + port + ":" + database + ":" + password);
		return new JedisPool(config, host, port, Protocol.DEFAULT_TIMEOUT, password, database);
	}

	@Bean
	public JedisPoolConfig jedisPoolConfig(@Value("${spring.redis.lettuce.pool.max-active}") int maxTotal,
			@Value("${spring.redis.lettuce.pool.max-idle}") int maxIdle,
			@Value("${spring.redis.lettuce.pool.max-wait}") int maxWaitMillis) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWaitMillis);
		return config;
	}
}