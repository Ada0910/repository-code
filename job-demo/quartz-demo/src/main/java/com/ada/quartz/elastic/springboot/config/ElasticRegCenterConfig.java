package com.ada.quartz.elastic.springboot.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 注册中心配置
 * <p/>
 *
 * @Date: 2023/2/12 19:56
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Configuration
public class ElasticRegCenterConfig {

    /**
     * Bean 的 initMethod 属性用来指定 Bean 初始化完成之后要执行的方法，用来替代
     * 继承 InitializingBean 接口，以便在容器启动的时候创建注册中心
     */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(
            @Value("${regCenter.serverList}") final String serverList,
            @Value("${regCenter.namespace}") final String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }
}
