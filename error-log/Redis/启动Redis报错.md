# 问题

今天在启动win10中启动Redis的时候，发现了以下的问题

```
Caused by: redis.clients.jedis.exceptions.JedisConnectionException: Could not get a resource from the pool
	at redis.clients.util.Pool.getResource(Pool.java:53)
	at redis.clients.jedis.JedisPool.getResource(JedisPool.java:99)
	at redis.clients.jedis.JedisPool.getResource(JedisPool.java:12)
	at org.springframework.data.redis.connection.jedis.JedisConnectionFactory.fetchJedisConnector(JedisConnectionFactory.java:191)
	... 65 more
Caused by: redis.clients.jedis.exceptions.JedisDataException: ERR Client sent AUTH, but no password is set
	at redis.clients.jedis.Protocol.processError(Protocol.java:117)
	at redis.clients.jedis.Protocol.process(Protocol.java:151)
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
```

# 分析

通过分析可以知道，是API连接的时候，有密码连接，而客户端却没有授权

# 解决

在redis.windows.conf 文件中搜索requirepass关键字，然后在requirepass后面添加密码，即

```
requirepass 密码
```
