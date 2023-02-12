package com.ada.job.elastic.simple;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * Script 类型作业意为脚本类型作业，支持 shell，python，perl 等所有类型脚本 --简单例子
 * <p/>
 *
 * @Date: 2023/2/12 18:09
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ScriptJobTest {
	public static void main(String[] args) {
		// ZK注册中心
		CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "ada-ejob-standalone"));
		regCenter.init();

		// 定义作业核心配置
		JobCoreConfiguration scriptJobCoreConfig = JobCoreConfiguration.newBuilder("MyScriptJob", "0/4 * * * * ?", 2).build();
		// 定义SCRIPT类型配置
		ScriptJobConfiguration scriptJobConfig = new ScriptJobConfiguration(scriptJobCoreConfig,"D:\\Code\\repository-code\\job-demo\\src\\main\\java\\com\\ada\\job\\elastic\\simple\\script.bat");

		// 作业分片策略
		// 基于平均分配算法的分片策略
		String jobShardingStrategyClass = AverageAllocationJobShardingStrategy.class.getCanonicalName();

		// 定义Lite作业根配置
		// LiteJobConfiguration scriptJobRootConfig = LiteJobConfiguration.newBuilder(scriptJobConfig).jobShardingStrategyClass(jobShardingStrategyClass).build();
		LiteJobConfiguration scriptJobRootConfig = LiteJobConfiguration.newBuilder(scriptJobConfig).build();

		// 构建Job
		new JobScheduler(regCenter, scriptJobRootConfig).init();
		// new JobScheduler(regCenter, scriptJobRootConfig, jobEventConfig).init();
	}
}
