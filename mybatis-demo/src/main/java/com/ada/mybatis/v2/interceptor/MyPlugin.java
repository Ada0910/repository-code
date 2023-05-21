package com.ada.mybatis.v2.interceptor;

import com.ada.mybatis.v2.annotation.Intercepts;
import com.ada.mybatis.v2.plugin.Interceptor;
import com.ada.mybatis.v2.plugin.Invocation;
import com.ada.mybatis.v2.plugin.Plugin;

import java.util.Arrays;

/**
 * 自定义插件
 */
@Intercepts("query")
public class MyPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String statement = (String) invocation.getArgs()[0];
        Object[] parameter = (Object[]) invocation.getArgs()[1];
        Class pojo = (Class) invocation.getArgs()[2];
        System.out.println("插件输出：SQL：["+statement+"]");
        System.out.println("插件输出：Parameters："+Arrays.toString(parameter));

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
