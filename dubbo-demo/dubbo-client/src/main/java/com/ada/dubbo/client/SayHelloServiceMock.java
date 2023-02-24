package com.ada.dubbo.client;

import com.ada.dubbo.ISayHelloService;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 
 * 服务降级处理的类：当服务发生降级时，就会调用这个类
 * <p/>
 *
 * @Date: 2023/2/23 19:42
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SayHelloServiceMock implements ISayHelloService {
    @Override
    public String sayHello() {
        return "服务端发生异常， 被降解了。返回兜底数据。。。";
    }
}
