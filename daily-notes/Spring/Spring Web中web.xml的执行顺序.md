# web.xml配置节点简介

## context-param

- 格式定义

```java
<context-param>
  <param-name>contextConfigLocation</param-name>
  <param-value>classpath:spring/spring-mybatis.xml</param-value>
</context-param>
```

- 作用
  该元素用来声明应用范围(整个WEB项目)内的上下文初始化参数。
  param-name 设定上下文的参数名称。必须是唯一名称
  param-value 设定的参数名称的值，这里的例子是指定spring配置文件的位置

## listener

- 格式定义
  
  ```java
  //listen-class 指定监听类，该类继承ServletContextListener 包含初始化方法contextInitialized(ServletContextEvent event) 和销毁方法contextDestoryed(ServletContextEvent event)
  <listener>
  <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  ```
- 作用
  该元素用来注册一个监听器类。可以收到事件什么时候发生以及用什么作为响应的通知。事件监听程序在建立、修改和删除会话或servlet环境时得到通知。常与context-param联合使用。

## filter

- 格式定义
  
  ```java
  <filter>
  <filter-name>CharacterEncodingFilter</filter-name>
  <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  <init-param>
  <param-name>encoding</param-name>
  <param-value>utf-8</param-value>
  </init-param>
  </filter>
  <filter-mapping>
  <filter-name>CharacterEncodingFilter</filter-name>
  <url-pattern>/*</url-pattern>
  </filter-mapping>
  ```
  
  - 作用
    用于指定WEB容器的过滤器， filter能够在一个请求到达servlet之前预处理用户请求，也可以在离开servlet时处理http响应；在执行servlet之前，首先执行filter程序，并为之做一些预处理工作；根据程序需要修改请求和响应；在servlet被调用之后截获servlet的执行。
    ## servlet
- 格式定义

```
//配置Spring MVC，指定处理请求的Servlet，有两种方式：
//1. 默认查找MVC配置文件的地址是：/WEB-INF/${servletName}-servlet.xml
//2. 可以通过配置修改MVC配置文件的位置，需要在配置DispatcherServlet时指定MVC配置文件的位置。
//这里使用的是第二种方式
<!-- Springmvc的核心控制器 -->

<servlet>
    <servlet-name>dispatchServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring/springmvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>dispatchServlet</servlet-name>
    <url-pattern>*.shtml</url-pattern>
</servlet-mapping>
```

- 作用： 创建并返回一个包含基于客户请求性质的动态内容的完整的html页面；
  创建可嵌入到现有的html页面中的一部分html页面（html片段）；
  读取客户端发来的隐藏数据；
  读取客户端发来的显示数据；
  与其他服务器资源（包括数据库和java的应用程序）进行通信；
  
  # web.xml加载过程（步骤）：
  
  1.启动web项目，容器（如Tomcat、Apache）会去读取它的配置文件web.xml 中的两个节点，context-param和listener。
  2.紧接着，容器将创建一个ServletContext（又称为：Servlet上下文），应用范围内即整个WEB项目都能使用这个Servlet上下文。
  3.容器将< context-param >转化为键值对，并交给ServletContext。
  4.容器创建< listener >中的类实例,即创建监听。（备注：listener定义的类可以是自定义的类但必须需要继承ServletContextListener）。
  5.在监听中会有contextInitialized(ServletContextEvent args)初始化方法,在这个方法中获得：ServletContext = ServletContextEvent.getServletContext(); context-param的值 = ServletContext.getInitParameter(“context-param的键”)； 在这个类中还必须有一个contextDestroyed(ServletContextEvent event) 销毁方法。用于关闭应用前释放资源，比如说数据库连接的关闭。
  6.得到这个context-param的值之后，你就可以做一些操作了。注意，这个时候你的WEB项目还没有完全启动完成。这个动作会比所有的Servlet都要早。
  7.换句话说,这个时候,你对 < context-param > 中的键值做的操作，将在你的WEB项目完全启动之前被执行。
  8.举例.你可能想在项目启动之前就打开数据库。那么这里就可以在< context-param >中设置数据库的连接方式，在监听类中初始化数据库的连接
  - 补充知识：ServletContext,是一个全局的储存信息的空间，服务器开始，其就存在，服务器关闭，其才释放。request，一个用户可有多个；session，一个用户一个；而servletContext，所有用户共用一个。所以，为了节省空间，提高效率，ServletContext中，要放必须的、重要的、所有用户需要共享的线程又是安全的一些信息。例如，一个购物网站，用户要访问商品的详细信息，如果放在session域，每个用户都要访问一遍数据库，这样效率太低；而放在ServletContext中，服务器一启动，就访问数据库将商品信息放入数据库，这样所有用户只需要通过上下文就能访问到商品的信息。

# web.xml节点加载顺序：

1. web.xml节点的加载顺序与它们在web.xml中位置的先后无关，即不会因为< filter >写在< context-param >前面就先加载< filter >。
2. 上文也提到到了，< context-param >用于对ServletContext提供键值对，即应用程序的上下文信息。而listener、servlet等节点在初始化的过程中会使用到这些上下文信息，所以最后我们得出web.xml节点的加载顺序应该为：context-param->listener->filter->servlet。
3. 对于某类配置节点而言，位置的先后是有要求的。以servlet举例，与servlet相关的配置节点是servlet-mapping，对于拥有相同配置节servlet-name的servlet和servlet-mapping来说，servlet-mapping必须在servlet后定义，否则当解析到servlet-mapping时，它的servlet-name还没有定义。web 容器启动时初始化每个 servlet时，是按照 servlet配置节出现的顺序来初始化的。
4. 最终结论： web.xml 的加载顺序是：[context-param -> listener -> filter -> servlet -> spring] ，而同类型节点之间的实际程序调用的时候的顺序是根据对应的 mapping 的顺序进行调用的。

