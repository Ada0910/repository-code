# 自定义一个starter

## 命名规范

规范点的话，是项目的工程名推荐命名为{name}-spring-boot-starter

如：format-spring-boot-starter

# 如何使用

（1）引入依赖

```
<!--        添加依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-test</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
```

（2）配置`META-INF/spring.factories`,进行spring装配
Spring Boot自动注入的奥秘就来源于 Spring Boot应用在启动过程中会通过 SpringFactoriesLoader 加载所有依赖的 `META-INF/spring.factories` 文件，通过一系列的处理流程最终将 spring.factories 文件中的定义的各种 beans 装载入 ApplicationContext容器

（3）书写配置类，将需要的类注入给Spring,交给Spring托管

具体代码见：ada-spring-boot-starter

