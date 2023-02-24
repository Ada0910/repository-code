package com.springboot.dubbo.provider;

import com.ada.dubbo.ISayHelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 *
 * <b><code></code></b>
 * <p/>
 * sayHello的接口实现类
 * 
 * loadbalance = "random" 负载均衡的策略，选择了随机算法
 * cluster = "failsafe" 集群容错策略，选择的是失败安全，出现异常时，直接忽略
 * <p/>
 *
 * @Date: 2023/2/23 19:42
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Service(loadbalance = "random",timeout = 50000,cluster = "failsafe")
public class SayHelloServiceImpl implements ISayHelloService {

    @Override
    public String sayHello() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Come in SayHello()");
        return "Hello Dubbo";
    }
}
