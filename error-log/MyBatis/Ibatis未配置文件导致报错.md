# 问题

今天在排查问题的时候，发现了这样的错误

```java
Mar 30, 2022 3:45:22 PM org.apache.catalina.core.StandardWrapperValve invoke
SEVERE: Servlet.service() for servlet [ipps_mvc] in context with path [/ipps] th
rew exception [Request processing failed; nested exception is org.springframewor
k.remoting.RemoteAccessException: Could not deserialize result from HTTP invoker
 remote service [
                                http://localhost:8181/.....（省略路径）/httpEx
pManAuditBusiness
                        ]; nested exception is java.lang.ClassNotFoundException:
 com.ibatis.sqlmap.client.SqlMapException] with root cause
java.lang.ClassNotFoundException: com.ibatis.sqlmap.client.SqlMapException
        at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClas
sLoaderBase.java:1892)
        at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClas
sLoaderBase.java:1735)
        at org.springframework.util.ClassUtils.forName(ClassUtils.java:258)
        at org.springframework.core.ConfigurableObjectInputStream.resolveClass(C
onfigurableObjectInputStream.java:75)
        at java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:161
2)
        at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1517)
        at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1
771)
        at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
        at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:19
90)
        at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
        at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1
798)
        at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
        at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:19
90)
        at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:1915)
        at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1
798)
        at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1350)
        at java.io.ObjectInputStream.readObject(ObjectInputStream.java:370)
        at org.springframework.remoting.httpinvoker.AbstractHttpInvokerRequestEx
ecutor.doReadRemoteInvocationResult(AbstractHttpInvokerRequestExecutor.java:290)
        at org.springframework.remoting.httpinvoker.AbstractHttpInvokerRequestEx
ecutor.readRemoteInvocationResult(AbstractHttpInvokerRequestExecutor.java:241)
        at org.springframework.remoting.httpinvoker.SimpleHttpInvokerRequestExec
utor.doExecuteRequest(SimpleHttpInvokerRequestExecutor.java:95)
        at org.springframework.remoting.httpinvoker.AbstractHttpInvokerRequestEx
ecutor.executeRequest(AbstractHttpInvokerRequestExecutor.java:136)
        at org.springframework.remoting.httpinvoker.HttpInvokerClientInterceptor
.executeRequest(HttpInvokerClientInterceptor.java:192)
        at org.springframework.remoting.httpinvoker.HttpInvokerClientInterceptor
.executeRequest(HttpInvokerClientInterceptor.java:174)
        at com.sunwah.ipps.web.security.MyHttpInvokerProxyFactoryBean.invoke(MyH
ttpInvokerProxyFactoryBean.java:41)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(
ReflectiveMethodInvocation.java:172)
        at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynami
cAopProxy.java:204)
        at com.sun.proxy.$Proxy179.queryPage(Unknown Source)
        at com.sunwah.ipps.web.partner.PartnerExpManAuditController.query(Partne
rExpManAuditController.java:89)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.
java:57)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAcces
sorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:606)
        at org.springframework.web.method.support.InvocableHandlerMethod.invoke(
InvocableHandlerMethod.java:219)
        at org.springframework.web.method.support.InvocableHandlerMethod.invokeF
orRequest(InvocableHandlerMethod.java:132)
        at org.springframework.web.servlet.mvc.method.annotation.ServletInvocabl
eHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:104)
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingH
andlerAdapter.invokeHandleMethod(RequestMappingHandlerAdapter.java:746)
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingH
andlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:687)
        at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapt
er.handle(AbstractHandlerMethodAdapter.java:80)
        at org.springframework.web.servlet.DispatcherServlet.doDispatch(Dispatch
erServlet.java:925)
        at org.springframework.web.servlet.DispatcherServlet.doService(Dispatche
rServlet.java:856)
        at org.springframework.web.servlet.FrameworkServlet.processRequest(Frame
workServlet.java:915)
        at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServ
let.java:822)
        at javax.servlet.http.HttpServlet.service(HttpServlet.java:650)
        at org.springframework.web.servlet.FrameworkServlet.service(FrameworkSer
vlet.java:796)
        at javax.servlet.http.HttpServlet.service(HttpServlet.java:731)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(Appl
icationFilterChain.java:303)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationF
ilterChain.java:208)
        at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52
)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(Appl
icationFilterChain.java:241)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationF
ilterChain.java:208)
        at org.springframework.security.web.FilterChainProxy.doFilterInternal(Fi
lterChainProxy.java:186)
        at org.springframework.security.web.FilterChainProxy.doFilter(FilterChai
nProxy.java:160)
        at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(D
elegatingFilterProxy.java:346)
        at org.springframework.web.filter.DelegatingFilterProxy.doFilter(Delegat
ingFilterProxy.java:259)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(Appl
icationFilterChain.java:241)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationF
ilterChain.java:208)
        at org.springframework.session.web.http.SessionRepositoryFilter.doFilter
Internal(SessionRepositoryFilter.java:164)
        at org.springframework.session.web.http.OncePerRequestFilter.doFilter(On
cePerRequestFilter.java:80)
        at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(D
elegatingFilterProxy.java:346)
        at org.springframework.web.filter.DelegatingFilterProxy.doFilter(Delegat
ingFilterProxy.java:259)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(Appl
icationFilterChain.java:241)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationF
ilterChain.java:208)
        at org.springframework.web.filter.CharacterEncodingFilter.doFilterIntern
al(CharacterEncodingFilter.java:88)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerR
equestFilter.java:107)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(Appl
icationFilterChain.java:241)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationF
ilterChain.java:208)
        at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperV
alve.java:219)
        at org.apache.catalina.core.StandardContextValve.invoke(StandardContextV
alve.java:110)
        at org.apache.catalina.authenticator.AuthenticatorBase.invoke(Authentica
torBase.java:506)
        at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.j
ava:169)
        at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.j
ava:103)
        at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:
962)
        at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineVal
ve.java:116)
        at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.jav
a:445)
        at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp
11Processor.java:1115)
        at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(
AbstractProtocol.java:637)
        at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoin
t.java:316)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.
java:1145)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor
.java:615)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskTh
read.java:61)
        at java.lang.Thread.run(Thread.java:745)
```

# 分析

项目用的是ibatis配置，然后使用Spring httpInvoker来调用的接口，由于SqlMap的配置中没有配置对应的路径，所以项目报了上面的错

# 解决方法

在SQLMap的配置文件中，配置所对应的XML的文件的路径，使用HTTP invoker调用接口的时候，就能调用到对应的xml文件
