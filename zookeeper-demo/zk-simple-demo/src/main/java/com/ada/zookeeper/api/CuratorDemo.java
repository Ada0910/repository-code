package com.ada.zookeeper.api;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * Zk 操作API的Demo
 * <p/>
 *
 * @Date: 2022/11/13 17:25
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class CuratorDemo {
	private static final String CONNECTION_STR = "127.0.0.1:2181";

	public static void main(String[] args) throws Exception {

		// 方式一：
		// CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient();


		/*
		 * 方式二：
		 * retryPolicy 重试策略
		 */
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTION_STR).sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

		//启动
		curatorFramework.start();

		// createData(curatorFramework);

		deleteData(curatorFramework);

		// 创建
		// curatorFramework.create();
		// //修改
		// curatorFramework.setData();
		// // 删除
		// curatorFramework.delete();
		// //查询
		// curatorFramework.getData();
	}

	/**
	 * 创建节点
	 */
	private static void createData(CuratorFramework curatorFramework) throws Exception {
		curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/data/program", "test".getBytes());
	}

	/**
	 * 更新节点数据
	 */
	private static void updateData(CuratorFramework curatorFramework) throws Exception {
		curatorFramework.setData().forPath("/data/program", "up".getBytes());
	}

	/**
	 * 删除数据
	 */
	private static void deleteData(CuratorFramework curatorFramework) throws Exception {
		Stat stat = new Stat();
		String value = new String(curatorFramework.getData().storingStatIn(stat).forPath("/data/program"));
		curatorFramework.delete().withVersion(stat.getVersion()).forPath("/data/program");
	}
}
