# 问题
- 今天启动nacos的demo的时候，报了以下的错误，我的环境是用了【springboot 2.6.4 】+【nacos-config-spring-boot-starter 0.2.7】

```
Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2022-03-21 23:03:37.552 ERROR 10136 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'nacosConfigurationPropertiesBinder': Bean instantiation via constructor failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.alibaba.boot.nacos.config.binder.NacosBootConfigurationPropertiesBinder]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/boot/context/properties/ConfigurationBeanFactoryMetadata
	at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:315) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:296) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1372) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1222) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:582) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:542) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:335) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$226/1037955032.getObject(Unknown Source) ~[na:na]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:333) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:953) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:918) ~[spring-context-5.3.16.jar:5.3.16]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:583) ~[spring-context-5.3.16.jar:5.3.16]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:145) ~[spring-boot-2.6.4.jar:2.6.4]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:740) [spring-boot-2.6.4.jar:2.6.4]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:415) [spring-boot-2.6.4.jar:2.6.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:303) [spring-boot-2.6.4.jar:2.6.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1312) [spring-boot-2.6.4.jar:2.6.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1301) [spring-boot-2.6.4.jar:2.6.4]
	at com.ada.nacosdemo.NacosDemoApplication.main(NacosDemoApplication.java:10) [classes/:na]
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.alibaba.boot.nacos.config.binder.NacosBootConfigurationPropertiesBinder]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/boot/context/properties/ConfigurationBeanFactoryMetadata
	at org.springframework.beans.BeanUtils.instantiateClass(BeanUtils.java:224) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:117) ~[spring-beans-5.3.16.jar:5.3.16]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:311) ~[spring-beans-5.3.16.jar:5.3.16]
	... 20 common frames omitted
Caused by: java.lang.NoClassDefFoundError: org/springframework/boot/context/properties/ConfigurationBeanFactoryMetadata
	at com.alibaba.boot.nacos.config.binder.NacosBootConfigurationPropertiesBinder.<init>(NacosBootConfigurationPropertiesBinder.java:51) ~[nacos-config-spring-boot-autoconfigure-0.2.7.jar:0.2.7]
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[na:1.8.0_31]
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62) ~[na:1.8.0_31]
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[na:1.8.0_31]
	at java.lang.reflect.Constructor.newInstance(Constructor.java:408) ~[na:1.8.0_31]
	at org.springframework.beans.BeanUtils.instantiateClass(BeanUtils.java:211) ~[spring-beans-5.3.16.jar:5.3.16]
	... 22 common frames omitted
Caused by: java.lang.ClassNotFoundException: org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata
	at java.net.URLClassLoader$1.run(URLClassLoader.java:372) ~[na:1.8.0_31]
	at java.net.URLClassLoader$1.run(URLClassLoader.java:361) ~[na:1.8.0_31]
	at java.security.AccessController.doPrivileged(Native Method) ~[na:1.8.0_31]
	at java.net.URLClassLoader.findClass(URLClassLoader.java:360) ~[na:1.8.0_31]
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424) ~[na:1.8.0_31]
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:308) ~[na:1.8.0_31]
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357) ~[na:1.8.0_31]
	... 28 common frames omitted

```

# 分析

## 原因
- 原本的配置是这样的

```
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.6.4</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

  <!--其他依赖-->

<dependency>
			<groupId>com.alibaba.boot</groupId>
			<artifactId>nacos-config-spring-boot-starter</artifactId>
			<version>0.2.7</version>
</dependency>
	
```

检查之后发现spring boot集成nacos config报错

```
NoClassDefFoundError: org/springframework/boot/context/properties/ConfigurationBeanFactoryMetadata
```

- 原因是我的spring boot版本是2.6.4 , nacos config的版本是0.2.7，两个版本不兼容导致，在spring boot 2.4之后删掉了ConfigurationBeanFactoryMetadata，将spring boot版本降级为2.3.9.RELEASE即可解决.
 
## 解决方法
```
<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.9.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

 <!--其他依赖-->

```