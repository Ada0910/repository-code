package com.ada.quartz.elastic.springboot.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 *
 * @Date: 2023/2/12 19:56
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyElasticJobListener implements ElasticJobListener {

    private long beginTime = 0;
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        beginTime = System.currentTimeMillis();

        System.out.println("===>{} JOB BEGIN TIME: {} <=== " + shardingContexts.getJobName() + beginTime);
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        long endTime = System.currentTimeMillis();
        System.out.println("===>{} JOB END TIME: {},TOTAL CAST: {} <===" + shardingContexts.getJobName() + endTime + (endTime - beginTime));
    }
}
