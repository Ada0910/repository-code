package com.ada.provider.spring.config;

import com.ada.provider.spring.server.RpcServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/15 0:07
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Configuration
@ComponentScan(basePackages = "com.ada.provider")//扫描这个包
public class SpringConfig {

	@Bean(name = "rpcServer")
	public RpcServer getRpcServer() {
		return new RpcServer(8080);
	}

}
