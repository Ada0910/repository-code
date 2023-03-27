package com.ada.sentinel.sentinelweb;

import com.ada.sentinel.SentinelService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 限流的controller
 * <p/>
 *
 * @Date: 2023/3/27 23:07
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@RestController
public class SentinelController {

    @Reference(timeout = 3000)
    SentinelService sentinelService;//proxy$0

    @GetMapping("/say")
    public String sayHello(){
        return sentinelService.sayHello("test");
    }
}
