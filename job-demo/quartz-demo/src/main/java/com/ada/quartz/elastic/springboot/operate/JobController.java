package com.ada.quartz.elastic.springboot.operate;

import com.ada.quartz.elastic.springboot.config.ElasticJobConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class JobController {
@Autowired
private ElasticJobConfig elasticJobConfig;

@RequestMapping("/addJob")
    public void addJob() {
        int shardingTotalCount = 2;
        // elasticJobConfig.addSimpleJobScheduler(new SimpleJobDemo().getClass(),"* * * * * ?",shardingTotalCount,"0=A,1=B");
    }




}
