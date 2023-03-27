package com.ada.sentinel.sentinelprovider;

import com.ada.sentinel.SentinelService;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 实现类(把当前服务发布成dubbo服务)
 * <p/>
 *
 * @Date: 2023/3/27 23:07
 * @Author xwn
 * @Version 1.0.0.1
 *
 */

@Service
public class SentinelServiceImpl implements SentinelService {

    @Override
    public String sayHello(String txt) {
        return "hello world :" + LocalDateTime.now();
    }
}
