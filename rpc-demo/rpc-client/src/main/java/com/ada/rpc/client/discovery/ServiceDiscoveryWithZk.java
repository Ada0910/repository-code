package com.ada.rpc.client.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/13 21:08
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ServiceDiscoveryWithZk implements IServerDiscovery {

	CuratorFramework curatorFramework = null;

	List<String> serviceRepos = new ArrayList<>(); //服务地址的本地缓存

	{
		//初始化zookeeper的连接， 会话超时时间是5s，衰减重试
		curatorFramework = CuratorFrameworkFactory.builder().connectString(ZkConfig.CONNECTION_STR).sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).namespace("registry").build();
		curatorFramework.start();
	}

	/**
	 * 服务的查找
	 * 设置监听
	 */
	@Override
	public String discovery(String serviceName) {
		//完成了服务地址的查找(服务地址被删除)
		String path = "/" + serviceName; //registry/com.ada.demo.HelloService
		if (serviceRepos.isEmpty()) {
			try {
				serviceRepos = curatorFramework.getChildren().forPath(path);
				registryWatch(path);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		//针对已有的地址做负载均衡
		ILoadBalanceStrategy loadBalanceStrategy = new RandomLoadBalance();
		return loadBalanceStrategy.selectHost(serviceRepos);
	}

	/**
	 * 设置监听
	 */
	private void registryWatch(String path) throws Exception {
		PathChildrenCache nodeCache = new PathChildrenCache(curatorFramework, path, true);
		PathChildrenCacheListener nodeCacheListener = (curatorFramework1, pathChildrenCacheEvent) -> {
			System.out.println("客户端收到节点变更的事件");
			serviceRepos = curatorFramework1.getChildren().forPath(path);// 再次更新本地的缓存地址
		};
		nodeCache.getListenable().addListener(nodeCacheListener);
		nodeCache.start();
	}
}
