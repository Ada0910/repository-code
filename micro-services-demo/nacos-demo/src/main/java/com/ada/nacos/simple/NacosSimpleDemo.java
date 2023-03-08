package com.ada.nacos.simple;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 *  Nacos简单的案例
 *
 * <p/>
 *
 * @Date: 2023/3/8 23:44
 * @Author xwn
 * @Version 1.0.0.1
 *
 *
 *
 */
public class NacosSimpleDemo {
	public static void main(String[] args) {
		String serverAddr = "localhost:8848";
		String dataId = "example";
		String groupId = "DEFAULT_GROUP";
		Properties proterties = new Properties();
		proterties.put("serverAddr", serverAddr);

		try {
			ConfigService configService = NacosFactory.createConfigService(proterties);
			String content = configService.getConfig(dataId, groupId, 3000);
			System.out.println(content);

			configService.addListener(dataId, groupId, new Listener() {
				@Override
				public Executor getExecutor() {
					return null;
				}

				@Override
				public void receiveConfigInfo(String s) {
					System.out.println("configInfo:"+s);
				}
			});
		} catch (NacosException e) {
			e.printStackTrace();
		}
	}
}
