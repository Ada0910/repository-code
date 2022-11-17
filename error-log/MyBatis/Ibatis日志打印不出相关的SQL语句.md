# 问题

项目用到了Log4j来打印日志，由于项目中的xml文件中配置了很多SQL语句，每次排查问题的时候，都要原生去拼接SQL语句，相当麻烦，还很容易出错，导致问题不能很好的定位，所以在日志中配置，把SQL语句打印出来，但是奇怪的是

配置完日志之后，发现查询语句还是没有打印出来（日志的配置是正常的）

下面是log4j的日志配置：

```java
log4j.logger.com.ibatis=DEBUG
log4j.logger.com.ibatis.common.jdbc.SimpleDataSource=DEBUG
log4j.logger.com.ibatis.common.jdbc.ScriptRunner=DEBUG
log4j.logger.java.sql.Connection=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG,stdout,ippsLog
```

# 分析

这是由于项目配置中可能含有其他日志依赖包，比如有slf4j，导致了log4j打印不出，排查之后，发现了项目中有org.slf4j:jcl-over-slf4j:1.7.25和org.slf4j:slf4j-api:1.6.0这两个依赖

# 解决

去掉项目中org.slf4j:jcl-over-slf4j:1.7.25和org.slf4j:slf4j-api:1.6.0这两个依赖，即可正常的打印出SQL语句
