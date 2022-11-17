# 问题

今天启动项目的时候，报了如下的错误：

```java
严重: 异常将上下文初始化事件发送到类的侦听器实例.[org.springframework.web.context.ContextLoaderListener]
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'adjAccInBusiness': Injection of resource dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sysUserService': Injection of resource dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sysUserDao': Injection of persistence dependencies failed; nested exception is 
Caused by: java.lang.NoClassDefFoundError: com/sunwah/ipps/api/intf/ICheckSyncStatusBusiness
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:800)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	at org.apache.catalina.loader.WebappClassLoaderBase.findClassInternal(WebappClassLoaderBase.java:3264)
	at org.apache.catalina.loader.WebappClassLoaderBase.findClass(WebappClassLoaderBase.java:1416)
	at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1920)
	at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1795)
	at org.springframework.util.ClassUtils.forName(ClassUtils.java:258)
	at org.springframework.beans.factory.support.AbstractBeanDefinition.resolveBeanClass(AbstractBeanDefinition.java:415)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doResolveBeanClass(AbstractBeanFactory.java:1284)
	at org.springframework.beans.factory.support.AbstractBeanFactory.resolveBeanClass(AbstractBeanFactory.java:1255)
	... 101 more
Caused by: java.lang.ClassNotFoundException: com.sunwah.ipps.api.intf.ICheckSyncStatusBusiness
	at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1951)
	at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1795)
	... 112 more
```

# 分析

这个是很典型的Caused by: java.lang.NoClassDefFoundError没有找到的问题，排查的项目的问题之后发现，相关的api模块没有引入

# 解决

将相关的api模块引入到项目工程中
