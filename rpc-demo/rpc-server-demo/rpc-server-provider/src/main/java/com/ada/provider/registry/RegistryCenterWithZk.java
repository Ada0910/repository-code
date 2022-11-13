package com.ada.provider.registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/13 20:28
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RegistryCenterWithZk implements IRegistryCenter {
	CuratorFramework curatorFramework = null;

	{
		//初始化zookeeper的连接， 会话超时时间是5s，衰减重试
		curatorFramework = CuratorFrameworkFactory.builder().connectString(ZkConfig.CONNECTION_STR).sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).namespace("registry").build();
		curatorFramework.start();
	}

	@Override
	public void registry(String serviceName, String serviceAddress) {
		String servicePath = "/" + serviceName;
		try {
			if (curatorFramework.checkExists().forPath(servicePath) == null) {
				curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(servicePath);
			}
			//serviceAddress: ip:port
			String addressPath = servicePath + "/" + serviceAddress;
			curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath);
			System.out.println("服务注册成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
