# 问题
今天在弄Dubbo+Zookeeper+Spring Boot的时候，遇到了以下问题：

```
java.lang.IllegalStateException: Failed to register dubbo://192.168.1.104:20880/com.dubbo.ISayHelloService?anyhost=true&application=springboot-dubbo&bean.name=ServiceBean:com.dubbo.ISayHelloService&deprecated=false&dubbo=2.0.2&dynamic=true&generic=false&interface=com.dubbo.ISayHelloService&methods=sayHello&pid=352&register=true&release=2.7.2&side=provider&timestamp=1647444854349 to registry 127.0.0.1:2181, cause: Failed to register dubbo://192.168.1.104:20880/com.dubbo.ISayHelloService?anyhost=true&application=springboot-dubbo&bean.name=ServiceBean:com.dubbo.ISayHelloService&deprecated=false&dubbo=2.0.2&dynamic=true&generic=false&interface=com.dubbo.ISayHelloService&methods=sayHello&pid=352&register=true&release=2.7.2&side=provider&timestamp=1647444854349 to zookeeper zookeeper://127.0.0.1:2181/org.apache.dubbo.registry.RegistryService?application=springboot-dubbo&dubbo=2.0.2&interface=org.apache.dubbo.registry.RegistryService&pid=352&qos-enable=false&release=2.7.2&timestamp=1647444854344, cause: KeeperErrorCode = Unimplemented for /dubbo
	at org.apache.dubbo.registry.support.FailbackRegistry.register(FailbackRegistry.java:249) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.registry.integration.RegistryProtocol.register(RegistryProtocol.java:185) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.registry.integration.RegistryProtocol.export(RegistryProtocol.java:219) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.rpc.protocol.ProtocolFilterWrapper.export(ProtocolFilterWrapper.java:120) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.qos.protocol.QosProtocolWrapper.export(QosProtocolWrapper.java:61) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.rpc.protocol.ProtocolListenerWrapper.export(ProtocolListenerWrapper.java:59) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.rpc.Protocol$Adaptive.export(Protocol$Adaptive.java) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.config.ServiceConfig.doExportUrlsFor1Protocol(ServiceConfig.java:607) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.config.ServiceConfig.doExportUrls(ServiceConfig.java:457) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.config.ServiceConfig.doExport(ServiceConfig.java:415) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.config.ServiceConfig.export(ServiceConfig.java:378) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.config.spring.ServiceBean.export(ServiceBean.java:336) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.config.spring.ServiceBean.onApplicationEvent(ServiceBean.java:114) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.config.spring.ServiceBean.onApplicationEvent(ServiceBean.java:60) ~[dubbo-2.7.2.jar:2.7.2]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:176) ~[spring-context-5.3.16.jar:5.3.16]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:169) ~[spring-context-5.3.16.jar:5.3.16]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:143) ~[spring-context-5.3.16.jar:5.3.16]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:421) ~[spring-context-5.3.16.jar:5.3.16]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:378) ~[spring-context-5.3.16.jar:5.3.16]
	at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:938) ~[spring-context-5.3.16.jar:5.3.16]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:586) ~[spring-context-5.3.16.jar:5.3.16]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:740) [spring-boot-2.6.4.jar:2.6.4]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:415) [spring-boot-2.6.4.jar:2.6.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:303) [spring-boot-2.6.4.jar:2.6.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1312) [spring-boot-2.6.4.jar:2.6.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1301) [spring-boot-2.6.4.jar:2.6.4]
	at com.ada.dubbo.provider.DubboProviderApplication.main(DubboProviderApplication.java:10) [classes/:na]
Caused by: org.apache.dubbo.rpc.RpcException: Failed to register dubbo://192.168.1.104:20880/com.dubbo.ISayHelloService?anyhost=true&application=springboot-dubbo&bean.name=ServiceBean:com.dubbo.ISayHelloService&deprecated=false&dubbo=2.0.2&dynamic=true&generic=false&interface=com.dubbo.ISayHelloService&methods=sayHello&pid=352&register=true&release=2.7.2&side=provider&timestamp=1647444854349 to zookeeper zookeeper://127.0.0.1:2181/org.apache.dubbo.registry.RegistryService?application=springboot-dubbo&dubbo=2.0.2&interface=org.apache.dubbo.registry.RegistryService&pid=352&qos-enable=false&release=2.7.2&timestamp=1647444854344, cause: KeeperErrorCode = Unimplemented for /dubbo
	at org.apache.dubbo.registry.zookeeper.ZookeeperRegistry.doRegister(ZookeeperRegistry.java:117) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.registry.support.FailbackRegistry.register(FailbackRegistry.java:236) ~[dubbo-2.7.2.jar:2.7.2]
	... 26 common frames omitted
Caused by: java.lang.IllegalStateException: KeeperErrorCode = Unimplemented for /dubbo
	at org.apache.dubbo.remoting.zookeeper.curator.CuratorZookeeperClient.createPersistent(CuratorZookeeperClient.java:98) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.remoting.zookeeper.support.AbstractZookeeperClient.create(AbstractZookeeperClient.java:71) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.remoting.zookeeper.support.AbstractZookeeperClient.create(AbstractZookeeperClient.java:66) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.remoting.zookeeper.support.AbstractZookeeperClient.create(AbstractZookeeperClient.java:66) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.remoting.zookeeper.support.AbstractZookeeperClient.create(AbstractZookeeperClient.java:66) ~[dubbo-2.7.2.jar:2.7.2]
	at org.apache.dubbo.registry.zookeeper.ZookeeperRegistry.doRegister(ZookeeperRegistry.java:115) ~[dubbo-2.7.2.jar:2.7.2]
	... 27 common frames omitted
Caused by: org.apache.zookeeper.KeeperException$UnimplementedException: KeeperErrorCode = Unimplemented for /dubbo
	at org.apache.zookeeper.KeeperException.create(KeeperException.java:103) ~[zookeeper-3.5.3-beta.jar:3.5.3-beta-8ce24f9e675cbefffb8f21a47e06b42864475a60]
	at org.apache.zookeeper.KeeperException.create(KeeperException.java:51) ~[zookeeper-3.5.3-beta.jar:3.5.3-beta-8ce24f9e675cbefffb8f21a47e06b42864475a60]
	at org.apache.zookeeper.ZooKeeper.create(ZooKeeper.java:1525) ~[zookeeper-3.5.3-beta.jar:3.5.3-beta-8ce24f9e675cbefffb8f21a47e06b42864475a60]
	at org.apache.curator.framework.imps.CreateBuilderImpl$17.call(CreateBuilderImpl.java:1181) ~[curator-framework-4.0.1.jar:4.0.1]
	at org.apache.curator.framework.imps.CreateBuilderImpl$17.call(CreateBuilderImpl.java:1158) ~[curator-framework-4.0.1.jar:4.0.1]
	at org.apache.curator.connection.StandardConnectionHandlingPolicy.callWithRetry(StandardConnectionHandlingPolicy.java:64) ~[curator-client-4.0.1.jar:na]
	at org.apache.curator.RetryLoop.callWithRetry(RetryLoop.java:100) ~[curator-client-4.0.1.jar:na]
	at org.apache.curator.framework.imps.CreateBuilderImpl.pathInForeground(CreateBuilderImpl.java:1155) ~[curator-framework-4.0.1.jar:4.0.1]
	at org.apache.curator.framework.imps.CreateBuilderImpl.protectedPathInForeground(CreateBuilderImpl.java:605) ~[curator-framework-4.0.1.jar:4.0.1]
	at org.apache.curator.framework.imps.CreateBuilderImpl.forPath(CreateBuilderImpl.java:595) ~[curator-framework-4.0.1.jar:4.0.1]
	at org.apache.curator.framework.imps.CreateBuilderImpl.forPath(CreateBuilderImpl.java:573) ~[curator-framework-4.0.1.jar:4.0.1]
	at org.apache.curator.framework.imps.CreateBuilderImpl.forPath(CreateBuilderImpl.java:49) ~[curator-framework-4.0.1.jar:4.0.1]
	at org.apache.dubbo.remoting.zookeeper.curator.CuratorZookeeperClient.createPersistent(CuratorZookeeperClient.java:95) ~[dubbo-2.7.2.jar:2.7.2]
	... 32 common frames omitted
```

# 分析
- 查看我本机的Zookeeper是版本是【3.4.12】
- 而maven项目中我用的依赖是
```
	<dependency>
			<groupId>org.apache.curator</groupId>
			<artifactId>curator-framework</artifactId>
			<version>4.0.1</version>
		</dependency>
```
- 查看报错日志是curator会引用了zookeeper-3.5.3-beta的版本，版本的差异导致了这样的问题
# 解决问题
在maven依赖中排出curator中引用的zookeeper-3.5.3-beta版本，然后引入自己本机的版本
将下面的maven依赖
```
	<dependency>
			<groupId>org.apache.curator</groupId>
			<artifactId>curator-framework</artifactId>
			<version>4.0.1</version>
		</dependency>
```
换成

```
<dependency>
			<groupId>org.apache.curator</groupId>
			<artifactId>curator-framework</artifactId>
			<version>4.0.1</version>
			<exclusions>
				<exclusion>
					<artifactId>zookeeper</artifactId>
					<groupId>org.apache.zookeeper</groupId>
				</exclusion>
			</exclusions>
		</dependency>
<dependency>
			<groupId>org.apache.zookeeper</groupId>
			<artifactId>zookeeper</artifactId>
			<version>3.4.12</version>
</dependency>
```