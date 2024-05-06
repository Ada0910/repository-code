package com.ada.distributed.session2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 *
 * <b><code></code></b>
 * <p/>
 * Session Config类
 * <p/>
 *
 * @Date: 2024/4/23 0:10
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
//这个类用配置redis服务器的连接
//maxInactiveIntervalInSeconds为SpringSession的过期时间（单位：秒）
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class SessionConfig2 {

	// 冒号后的值为没有配置文件时，制动装载的默认值
	@Value("${redis.hostname:localhost}")
	private String hostName;
	@Value("${redis.port:6379}")
	private int port;
	@Value("${redis.password}")
	private String password;

	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connection = new JedisConnectionFactory();
		connection.setPort(port);
		connection.setHostName(hostName);
		connection.setPassword(password);
		connection.setDatabase(0);
		return connection;
	}
}