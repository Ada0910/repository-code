package com.ada.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * <b><code></code></b>
 * <p/>
 *  客户端打印类
 * <p/>
 *
 * @Date: 2023/03/30 23:55
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@FeignClient("config-client")
public interface EchoServiceClient {
    
    
    @GetMapping(value = "/services/name")
    String services();
}
