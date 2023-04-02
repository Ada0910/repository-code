package com.ada.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * <b><code></code></b>
 * <p/>
 *  客户端打印类
 *
 *   @FeignClient("config-client")  说明调用名称叫config-client的服务
 *
 * <p/>
 *
 * @Date: 2023/03/30 23:55
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@FeignClient("config-client")
public interface EchoServiceClient {
    
    /**
     *  使用该方法就相当于是要调用服务名称叫config-client，然后/services/name的controller调用方法
     */
    @GetMapping(value = "/services/name")
    String services();
}
