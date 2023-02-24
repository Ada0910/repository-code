package com.ada.dubbo.client;

import com.ada.dubbo.ISayHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 客户端controller层
 * 
 * loadbalance = "roundrobin"：加权轮询算法
 * cluster：集群容错，failfast快速失败，常用于新增记录
 * mock：配置当服务发生降级的时候，会调用的类
 * timeout：超时的时间（毫秒）
 * check：Dubbo 缺省会在启动时检查依赖的服务是否可用，不可用时会抛出异常，阻止 Spring初始化完成，以便上线时，能及早发现问题，默认 check="true"
 *        check="false" ：关闭检查，比如，测试时，有些服务不关心，或者出现了循环依赖，必须有一方先启动
 * <p/>
 *
 * @Date: 2023/2/23 19:42
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@RestController
public class DubboController {
    //Dubbo提供的注解
    @Reference(loadbalance = "roundrobin",timeout = 1,cluster ="failfast",mock = "com.ada.dubbo.client.SayHelloServiceMock",check = false)
    ISayHelloService sayHelloService; //dubbo://

    @GetMapping("/sayhello")
    public String sayHello() throws InterruptedException {
        return sayHelloService.sayHello(); //我调用这个服务可能失败，如果失败了，我要怎么处理
    }



}
