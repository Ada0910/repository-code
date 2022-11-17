# 问题

启动项目报以下的问题

```java
[2022-03-28 16:13:39 588][ERROR][org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:307)]Context initialization failed
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'enableRedisKeyspaceNotificationsInitializer' defined in class path resource [org/springframework/session/data/redis/config/annotation/web/http/RedisHttpSessionConfiguration.class]: Invocation of init method failed; nested exception is org.springframework.data.redis.RedisConnectionFailureException: Cannot get Jedis connection; nested exception is redis.clients.jedis.exceptions.JedisConnectionException: Could not get a resource from the pool
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1486)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:524)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:461)
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:295)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:223)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:292)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:194)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:607)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:932)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:479)
	at org.springframework.web.context.ContextLoader.configureAndRefreshWebApplicationContext(ContextLoader.java:383)
	at org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:283)
	at org.springframework.web.context.ContextLoaderListener.contextInitialized(ContextLoaderListener.java:112)
	at org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:5128)
	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5653)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
	at org.apache.catalina.core.ContainerBase.addChildInternal(ContainerBase.java:1007)
	at org.apache.catalina.core.ContainerBase.addChild(ContainerBase.java:983)
	at org.apache.catalina.core.StandardHost.addChild(StandardHost.java:639)
	at org.apache.catalina.startup.HostConfig.manageApp(HostConfig.java:1899)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.apache.tomcat.util.modeler.BaseModelMBean.invoke(BaseModelMBean.java:302)
	at com.sun.jmx.interceptor.DefaultMBeanServerInterceptor.invoke(DefaultMBeanServerInterceptor.java:819)
	at com.sun.jmx.mbeanserver.JmxMBeanServer.invoke(JmxMBeanServer.java:801)
	at org.apache.catalina.mbeans.MBeanFactory.createStandardContext(MBeanFactory.java:636)
	at org.apache.catalina.mbeans.MBeanFactory.createStandardContext(MBeanFactory.java:580)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
三月 28, 2022 4:13:39 下午 org.apache.catalina.core.StandardContext startInternal
严重: 一个或多个listeners启动失败，更多详细信息查看对应的容器日志文件
三月 28, 2022 4:13:39 下午 org.apache.catalina.core.StandardContext startInternal
严重: 由于之前的错误，Context[/ipps]启动失败
	at org.apache.tomcat.util.modeler.BaseModelMBean.invoke(BaseModelMBean.java:302)
	at com.sun.jmx.interceptor.DefaultMBeanServerInterceptor.invoke(DefaultMBeanServerInterceptor.java:819)
	at com.sun.jmx.mbeanserver.JmxMBeanServer.invoke(JmxMBeanServer.java:801)
	at com.sun.jmx.remote.security.MBeanServerAccessController.invoke(MBeanServerAccessController.java:468)
	at javax.management.remote.rmi.RMIConnectionImpl.doOperation(RMIConnectionImpl.java:1487)
	at javax.management.remote.rmi.RMIConnectionImpl.access$300(RMIConnectionImpl.java:97)
	at javax.management.remote.rmi.RMIConnectionImpl$PrivilegedOperation.run(RMIConnectionImpl.java:1328)
	at java.security.AccessController.doPrivileged(Native Method)
	at javax.management.remote.rmi.RMIConnectionImpl.doPrivilegedOperation(RMIConnectionImpl.java:1427)
	at javax.management.remote.rmi.RMIConnectionImpl.invoke(RMIConnectionImpl.java:848)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at sun.rmi.server.UnicastServerRef.dispatch(UnicastServerRef.java:322)
	at sun.rmi.transport.Transport$2.run(Transport.java:202)
	at sun.rmi.transport.Transport$2.run(Transport.java:199)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.rmi.transport.Transport.serviceCall(Transport.java:198)
	at sun.rmi.transport.tcp.TCPTransport.handleMessages(TCPTransport.java:567)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(TCPTransport.java:828)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.access$400(TCPTransport.java:619)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$1.run(TCPTransport.java:684)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$1.run(TCPTransport.java:681)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(TCPTransport.java:681)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
Caused by: org.springframework.data.redis.RedisConnectionFailureException: Cannot get Jedis connection; nested exception is redis.clients.jedis.exceptions.JedisConnectionException: Could not get a resource from the pool
	at org.springframework.data.redis.connection.jedis.JedisConnectionFactory.fetchJedisConnector(JedisConnectionFactory.java:198)
	at org.springframework.data.redis.connection.jedis.JedisConnectionFactory.getConnection(JedisConnectionFactory.java:345)
	at org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration$EnableRedisKeyspaceNotificationsInitializer.afterPropertiesSet(RedisHttpSessionConfiguration.java:223)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1545)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1483)
	... 61 more
Caused by: redis.clients.jedis.exceptions.JedisConnectionException: Could not get a resource from the pool
	at redis.clients.util.Pool.getResource(Pool.java:53)
	at redis.clients.jedis.JedisPool.getResource(JedisPool.java:99)
	at redis.clients.jedis.JedisPool.getResource(JedisPool.java:12)
	at org.springframework.data.redis.connection.jedis.JedisConnectionFactory.fetchJedisConnector(JedisConnectionFactory.java:191)
	... 65 more
Caused by: redis.clients.jedis.exceptions.JedisConnectionException: java.net.SocketException: Connection reset
	at redis.clients.util.RedisInputStream.ensureFill(RedisInputStream.java:201)
	at redis.clients.util.RedisInputStream.readByte(RedisInputStream.java:40)
	at redis.clients.jedis.Protocol.process(Protocol.java:141)
[2022-03-28 04:13:39,684] Artifact ipps exploded: Error during artifact deployment. See server log for details.
	at redis.clients.jedis.Protocol.read(Protocol.java:205)
	at redis.clients.jedis.Connection.readProtocolWithCheckingBroken(Connection.java:297)
	at redis.clients.jedis.Connection.getStatusCodeReply(Connection.java:196)
	at redis.clients.jedis.BinaryJedis.auth(BinaryJedis.java:2049)
	at redis.clients.jedis.JedisFactory.makeObject(JedisFactory.java:89)
	at org.apache.commons.pool2.impl.GenericObjectPool.create(GenericObjectPool.java:868)
	at org.apache.commons.pool2.impl.GenericObjectPool.borrowObject(GenericObjectPool.java:435)
	at org.apache.commons.pool2.impl.GenericObjectPool.borrowObject(GenericObjectPool.java:363)
	at redis.clients.util.Pool.getResource(Pool.java:49)
	... 68 more
Caused by: java.net.SocketException: Connection reset
	at java.net.SocketInputStream.read(SocketInputStream.java:196)
	at java.net.SocketInputStream.read(SocketInputStream.java:122)
	at java.net.SocketInputStream.read(SocketInputStream.java:108)
	at redis.clients.util.RedisInputStream.ensureFill(RedisInputStream.java:195)
	... 79 more
```

# 分析

这个是很典型的Redis密码错误

# 解决

换成正确的Redis密码即可
