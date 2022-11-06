package com.ada.jvm.stack;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>
 *  查看方法区的情况
 *
 *  内存参数设置为：-XX:MetaspaceSize=50M  -XX:MaxMetaspaceSize=50M
 *
 *  会报这个错误：java.lang.OutOfMemoryError: Metaspace
 * <p/>
 *
 * @Date: 2022/11/6 10:41
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@RestController
public class MetaspaceController {

    List<Class<?>> list=new ArrayList<Class<?>>();
    @GetMapping("/meta")
    public String heap() throws Exception{
        while(true){
            list.addAll(MetaspaceUtil.createClasses());
            Thread.sleep(5);
        }
    }
}
