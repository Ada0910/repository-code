package com.ada.quartz.elastic.simple;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.commons.dbcp.BasicDataSource;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 简单的任务执行样例
 * <p/>
 *
 * @Date: 2023/2/12 17:20
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MySimpleJob implements SimpleJob {
	@Override
	public void execute(ShardingContext context) {
		System.out.println(String.format("分片项 ShardingItem: %s | 运行时间: %s | 线程ID: %s | 分片参数: %s ",
				context.getShardingItem(), new SimpleDateFormat("HH:mm:ss").format(new Date()),
				Thread.currentThread().getId(), context.getShardingParameter()));
	}

	public static void main(String[] args) {
		CoordinatorRegistryCenter registryCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("","ada-ejob"));
		registryCenter.init();

		// 数据源，使用DBCP
        // BasicDataSource dataSource = new BasicDataSource();
        // dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        // dataSource.setUrl("jdbc:mysql://localhost:3306/elastic_job_log");
        // dataSource.setUsername("root");
        // dataSource.setPassword("root");
        // JobEventConfiguration jobEventConfig = new JobEventRdbConfiguration(dataSource);

		// 定义作业核心配置
		// TODO 如果修改了代码，跑之前清空ZK
		JobCoreConfiguration  coreConfig = JobCoreConfiguration.newBuilder("MySimpleJob", "0/2 * * * * ?", 4).shardingItemParameters("0=RDP, 1=CORE, 2=SIMS, 3=ECIF").failover(true).build();
		// 定义SIMPLE类型配置
		SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, MySimpleJob.class.getCanonicalName());
		// 作业分片策略
		// 基于平均分配算法的分片策略
		String jobShardingStrategyClass =AverageAllocationJobShardingStrategy.class.getCanonicalName();

		// 定义Lite作业根配置
		LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).jobShardingStrategyClass(jobShardingStrategyClass).build();
		// LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();

		// 构建Job
		new JobScheduler(registryCenter, simpleJobRootConfig).init();
		// new JobScheduler(registryCenter, simpleJobRootConfig, jobEventConfig).init();
	}
}
