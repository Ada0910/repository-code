package com.ada.mybatis.v1;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 *  配置类，获取配置信息
 */
public class MyConfiguration {
    public static final ResourceBundle sqlMappings;

    static{
        sqlMappings = ResourceBundle.getBundle("v1sql");
    }

    public <T> T getMapper(Class clazz, MySqlSession sqlSession) {
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz},
                new MyMapperProxy(sqlSession));
    }


}
