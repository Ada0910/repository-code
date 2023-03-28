package com.ada.config.simple;

import java.io.IOException;
import java.util.Properties;


/**
 *
 * <b><code></code></b>
 * <p/>
 * 配置demo
 * <p/>
 *
 * @Date: 2023/03/28 23:55
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
// Spring Boot application.properties
public class PropertiesDemo {

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        properties.setProperty("name", "ada");
        properties.setProperty("age", "34");

        properties.storeToXML(System.out, "This is a comment", "UTF-8");

    }
}
