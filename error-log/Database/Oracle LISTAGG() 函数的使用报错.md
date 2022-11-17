# 问题

今天在测试中发现了程序报了下面的错误

```java
--- The error occurred while applying a parameter map.  
--- Check the ReverseApprovedAudit.findWithPage-InlineParameterMap.  
--- Check the statement (query failed).  
--- Cause: java.sql.SQLException: ORA-01489: 字符串连接的结果过长

	at com.ibatis.sqlmap.engine.mapping.statement.MappedStatement.executeQueryWithCallback(MappedStatement.java:201)
	at com.ibatis.sqlmap.engine.mapping.statement.MappedStatement.executeQueryForList(MappedStatement.java:139)
	at com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate.queryForList(SqlMapExecutorDelegate.java:567)
	at com.ibatis.sqlmap.engine.impl.SqlMapSessionImpl.queryForList(SqlMapSessionImpl.java:126)
	at org.springframework.orm.ibatis.SqlMapClientTemplate$4.doInSqlMapClient(SqlMapClientTemplate.java:315)
	at org.springframework.orm.ibatis.SqlMapClientTemplate$4.doInSqlMapClient(SqlMapClientTemplate.java:313)
	at org.springframework.orm.ibatis.SqlMapClientTemplate.execute(SqlMapClientTemplate.java:203)
	... 58 more
Caused by: java.sql.SQLException: ORA-01489: 字符串连接的结果过长

	at oracle.jdbc.driver.T4CTTIoer.processError(T4CTTIoer.java:447)
	at oracle.jdbc.driver.T4CTTIoer.processError(T4CTTIoer.java:396)
	at oracle.jdbc.driver.T4C8Oall.processError(T4C8Oall.java:951)
	at oracle.jdbc.driver.T4CTTIfun.receive(T4CTTIfun.java:513)
	at oracle.jdbc.driver.T4CTTIfun.doRPC(T4CTTIfun.java:227)
	at oracle.jdbc.driver.T4C8Oall.doOALL(T4C8Oall.java:531)
	at oracle.jdbc.driver.T4CPreparedStatement.doOall8(T4CPreparedStatement.java:208)
	at oracle.jdbc.driver.T4CPreparedStatement.executeForDescribe(T4CPreparedStatement.java:886)
	at oracle.jdbc.driver.OracleStatement.executeMaybeDescribe(OracleStatement.java:1175)
	at oracle.jdbc.driver.OracleStatement.doExecuteWithTimeout(OracleStatement.java:1296)
	at oracle.jdbc.driver.OraclePreparedStatement.executeInternal(OraclePreparedStatement.java:3613)
	at oracle.jdbc.driver.OraclePreparedStatement.execute(OraclePreparedStatement.java:3714)
	at oracle.jdbc.driver.OraclePreparedStatementWrapper.execute(OraclePreparedStatementWrapper.java:1378)
	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3051)
	at com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_execute(FilterEventAdapter.java:440)
	at com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:3049)
	at com.alibaba.druid.proxy.jdbc.PreparedStatementProxyImpl.execute(PreparedStatementProxyImpl.java:167)
	at com.alibaba.druid.pool.DruidPooledPreparedStatement.execute(DruidPooledPreparedStatement.java:498)
	at sun.reflect.GeneratedMethodAccessor53.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at com.ibatis.common.jdbc.logging.PreparedStatementLogProxy.invoke(PreparedStatementLogProxy.java:62)
	at com.sun.proxy.$Proxy495.execute(Unknown Source)
	at com.ibatis.sqlmap.engine.execution.SqlExecutor.executeQuery(SqlExecutor.java:185)
	at com.ibatis.sqlmap.engine.mapping.statement.MappedStatement.sqlExecuteQuery(MappedStatement.java:221)
	at com.ibatis.sqlmap.engine.mapping.statement.MappedStatement.executeQueryWithCallback(MappedStatement.java:189)
	... 64 more
```

# 分析

导致的原因主要是处理业务逻辑的代码用了oracle中的函数，导致了拼接之后的字符串过长

下面看看Oracle LISTAGG() 聚合查询用法 GROUP BY的用法，就能知道这个报错是什么引起的

## Oracle LISTAGG() 聚合查询用法 GROUP BY

- 例子

TEST_USER表

| ID  | Name |
| --- | ---- |
| 111 | aaa  |
| 222 | bbb  |
| 333 | ccc  |

TEST_RECORD 表记录如下:

| ID  | TAG   | values |
| --- | ----- | ------ |
| 111 | start | 1      |
| 111 | mid   | 2      |
| 111 | end   | 3      |
| 222 | start | 1      |
| 222 | end   | 2      |
| 333 | start | 1      |
| 333 | mid   | 2      |
| 333 | end   | 3      |


期望的结果：


|  ID | NAME | AGG_VALUES |
| --: | :--: | ---------: |
| 111 | aaa |        1,3 |
| 222 | bbb |        1,2 |
| 333 | ccc |        1,3 |


在工作中用到的SQL语句大致就如下

```
SELECT u.ID, u.NAME, LISTAGG(r.VALUE, ',') WITHIN GROUP (ORDER BY r.VALUE) AS AGG_VALUES
FROM TEST_USER u LEFT OUTER JOIN TEST_RECORD r ON u.ID = r.ID
WHERE r.TAG IN ('start', 'end')
GROUP BY u.ID, u.NAME;
```


因此在业务场景中，当两个表TEST_USER和TEST_RECORD的ID的值为某一个数据，且这个数据量特别大时，那么AGG_VALUES的所拼接成的值会越大，大到一定程度的时候，会造成oracle报错，也就是上面的连接字符串过长

# 解决

根据自己的业务场景来适当的适用Oracle LISTAGG()oracle中的listagg()函数
