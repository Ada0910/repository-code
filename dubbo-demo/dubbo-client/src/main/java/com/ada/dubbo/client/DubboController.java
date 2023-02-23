package com.ada.dubbo.client;

import com.ada.dubbo.ISayHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
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
