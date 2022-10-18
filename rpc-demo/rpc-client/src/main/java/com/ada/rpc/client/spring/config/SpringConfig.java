package com.ada.rpc.client.spring.config;

import com.ada.rpc.client.spring.proxy.RpcProxyClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/15 0:30
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Configuration
public class SpringConfig {

	@Bean(name = "rpcProxyClient")
	public RpcProxyClient getRpcProxyClient() {
		return new RpcProxyClient();
	}
}
