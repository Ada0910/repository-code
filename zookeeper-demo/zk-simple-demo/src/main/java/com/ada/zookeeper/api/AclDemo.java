package com.ada.zookeeper.api;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 权限操作相关DEMO
 * <p/>
 *
 * @Date: 2022/11/13 18:02
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class AclDemo {
	private static final String CONNECTION_STR = "127.0.0.1:2181";

	public static void main(String[] args) throws Exception {

		// 方式一：
		// CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient();


		/*
		 * 方式二：
		 * retryPolicy 重试策略
		 */
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTION_STR).sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

		curatorFramework.start();

		setSimpleAclPerm(curatorFramework);

		//设置权限
		// setAclPerm(curatorFramework);

	}

	private static void setSimpleAclPerm(CuratorFramework curatorFramework) throws Exception {
		// 构造一个ACL的列表
		List<ACL> list = new ArrayList<>();
		ACL acl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.READ, new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
		list.add(acl);
		curatorFramework.create().withMode(CreateMode.PERSISTENT).withACL(list).forPath("/auth");

	}

	private static void setAclPerm(CuratorFramework curatorFramework) throws Exception {
		List<ACL> list = new ArrayList<>();
		ACL acl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
		list.add(acl);
		curatorFramework.setACL().withACL(ZooDefs.Ids.CREATOR_ALL_ACL).forPath("/temp");
	}
}
