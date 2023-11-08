# 手写一个starter

相当于是一个模块
（1）starter的命名规则
非官方：{name}-spring-boot-starter

# 日志的starter

（1）log4j --- apache基金会（最早出现）
（2）sun公司开发出 java.util.logging
(3) apache 整合上面，抽象出 commons loggging(接口)---可以用（1）或者（2）
（4）slf4j （门面）（log4j原作者又开发出来这个）logback （slf4j+logback ）
(5)log4j 升级成 log4j2

## 日志系统发展的历史

最早的日志组件是 Apache 基金会提供的 Log4j，log4j 能够通过配置文件轻松的实现日志系统的管理和多样化配置，所以很快被广泛运用。也是我们接触得比较早和比较多的
日志组件。它几乎成了 Java 社区的日志标准。

据说 Apache 基金会还曾经建议 Sun 引入 Log4j 到 java 的标准库中，但 Sun 拒绝了。 所以 sun 公司在 java1.4 版本中，增加了日志库(Java Util Logging)。其实现基本模仿了Log4j 的实现。在 JUL 出来以前，Log4j 就已经成为一项成熟的技术，使得 Log4j 在选择上占据了一定的优势Apache 推出的 JUL 后，有一些项目使用 JUL，也有一些项目使用 log4j，这样就造成了开发者的混乱，因为这两个日志组件没有关联，所以要想实现统一管理或者替换就非常困难。怎么办呢？

这个状况交给你来想想办法，你该如何解决呢？进行抽象，抽象出一个接口层，对每个日志实现都适配，这样这些提供给别人的库都直接使用抽象层即可这个时候又轮到 Apache 出手了，它推出了一个 Apache Commons Logging 组件，JCL 只是定义了一套日志接口(其内部也提供一个 Simple Log 的简单实现)，支持运行时动态加载日志组件的实现，也就是说，在你应用代码里，只需调用 Commons Logging 的接口，底层实现可以是Log4j，也可以是 Java Util Logging由于它很出色的完成了主流日志的兼容，所以基本上在后面很长一段时间，是无敌的存在。连 spring 也都是依赖 JCL进行日志管理

但是故事并没有结束原 Log4J 的作者，它觉得 Apache Commons Logging 不够优秀，所以他想搞一套更优雅的方案，于是 slf4j 日志体系诞生了，slf4j 实际上就是一个日志门面接口，它的作用类似于 Commons Loggins。 并且他还为 slf4j 提供了一个日志的实现-logback。因此大家可以发现 Java 的日志领域被划分为两个大营：Commons Logging 和 slf4j

另外，还有一个 log4j2 是怎么回事呢？ 因为 slf4j 以及它的实现 logback 出来以后，很快就赶超了原本 apache 的log4j 体系，所以 apache 在 2012 年重写了 log4j， 成立了新的项目 Log4j2总的来说，日志的整个体系分为日志框架和日志系统

日志框架：JCL/ Slf4j
日志系统：Log4j、Log4j2、Logback、JUL。
而在我们现在的应用中，绝大部分都是使用 slf4j 作为门面，
然后搭配 logback 或者 log4j2 日志系统

# Actuator

监控
在 spring-boot 项目中，添加 actuator 的一个 starter.
<dependency>
<groupId>org.springframework.boot</ groupId>
<artifactId>spring-boot-starter-actuator</ artifactId>
</dependency>

访问http://localhost:8080/actuator
可以看到非常多的 Endpoint。 有一些 Endpoint 是不能访
问的，涉及到安全问题。如 果 想 开 启 访 问 那 些 安 全 相 关 的 url ， 可 以 在application.xml 中配置， 开启所有的 endpoint
management.endpoints.web.exposure.include=* *

PS：输入jconsole可以打开控制器

# 什么是  JMX

JMX 全称是 Java Management Extensions。 Java 管理扩展。 它提供了对 Java 应用程序和 JVM 的监控和管理功能。通过 JMX，我们可以监控
1. 服务器中的各种资源的使用情况，CPU、内存
2. JVM 内存的使用情况
3. JVM 线程使用情况









