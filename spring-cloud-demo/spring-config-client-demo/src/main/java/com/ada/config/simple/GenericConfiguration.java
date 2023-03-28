package com.ada.config.simple;


/**
 *
 * <b><code></code></b>
 * <p/>
 *  基础配置类DEMO
 * <p/>
 *
 * @Date: 2023/03/28 23:55
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class GenericConfiguration {

    public static void main(String[] args) {

        println(System.getProperty("user.home"));

        println(System.getProperty("user.age", "0"));
        // 将 System Properties 转换为 Integer 类型
        println(Integer.getInteger("user.age", 0));
        println(Boolean.getBoolean("user.male"));
    }

    private static void println(Object object) {
        System.out.println(object);
    }

}
