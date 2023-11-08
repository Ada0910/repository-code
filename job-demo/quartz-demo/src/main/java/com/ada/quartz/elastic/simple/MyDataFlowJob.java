

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

import java.util.Arrays;
import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * Dataflow 类型用于处理数据流，必须实现 fetchData()和processData()的方法，一个用来获取数据，一个用来处理获取到的数据
 * <p/>
 *
 * @Date: 2023/2/12 18:01
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyDataFlowJob implements DataflowJob<String> {

	/**
	 * 获取数据
	 */
	@Override
	public List<String> fetchData(ShardingContext shardingContext) {
		System.out.println("开始获取数据");
		return Arrays.asList("ada", "jack", "seven");
	}


	/**
	 * 用来处理获取到的数据
	 */
	@Override
	public void processData(ShardingContext shardingContext, List<String> list) {
		for (String val : list) {
			// 处理完数据要移除掉，不然就会一直跑
			System.out.println("开始处理数据：" + val);
		}
	}

	public static void main(String[] args) {
		// ZK注册中心
		CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "ada-stand-data"));
		regCenter.init();

		// 定义作业核心配置
		JobCoreConfiguration dataJobCoreConfig = JobCoreConfiguration.newBuilder("MyDataFlowJob", "0/4 * * * * ?", 2).build();
		// 定义DATAFLOW类型配置
		DataflowJobConfiguration dataJobConfig = new DataflowJobConfiguration(dataJobCoreConfig, MyDataFlowJob.class.getCanonicalName(), true);

		// 作业分片策略
		// 基于平均分配算法的分片策略
		String jobShardingStrategyClass = AverageAllocationJobShardingStrategy.class.getCanonicalName();

		// 定义Lite作业根配置
		 LiteJobConfiguration dataflowJobRootConfig = LiteJobConfiguration.newBuilder(dataJobConfig).jobShardingStrategyClass(jobShardingStrategyClass).build();
		// LiteJobConfiguration dataflowJobRootConfig = LiteJobConfiguration.newBuilder(dataJobConfig).build();

		// 构建Job
		new JobScheduler(regCenter, dataflowJobRootConfig).init();
		// new JobScheduler(regCenter, dataflowJobRootConfig, jobEventConfig).init();
	}
}
