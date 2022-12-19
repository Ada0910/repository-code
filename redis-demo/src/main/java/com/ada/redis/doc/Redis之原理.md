# 发布订阅模式

## 列表的局限

前面我们说通过队列的 rpush 和 lpop 可以实现消息队列（队尾进队头出），但是消费者需要不停地调用 lpop 查看 List 中是否有等待处理的消息（比如写一个 while 循环）。为了减少通信的消耗，可以sleep()一段时间再消费，但是会有两个问题

1、如果生产者生产消息的速度远大于消费者消费消息的速度，List 会占用大量的内存。

2、消息的实时性降低。
list 还提供了一个阻塞的命令：blpop，没有任何元素可以弹出的时候，连接会被阻塞

基于 list 实现的消息队列，不支持一对多的消息分发

## 发布订阅模式

除了通过 list 实现消息队列之外，Redis 还提供了一组命令实现发布/订阅模式。

```
subscribe channel-1 channel-2 channel-3
```

发布者可以向指定频道发布消息（并不支持一次向多个频道发送消息）：

```
publish channel-1 2673：
```

取消订阅（不能在订阅状态下使用）

```
unsubscribe channel-1
```

### 按规则来订阅频道

支持?和*占位符。?代表一个字符，*代表 0 个或者多个字符。

消费端 1，关注运动信息

```
psubscribe *sport
```

消费端 2，关注所有新闻：

```
psubscribe news*
```

消费端 3，关注天气新闻

```
psubscribe news-weather
```

生产者，发布 3 条信息

```
publish news-sport yaoming
publish news-music jaychou
publish news-weather rain
```

![image.png](./assets/1671465332546-image.png)

# Redis事务

## 为啥要用Redis事务，也就是Redis事务有什么作用？

我们知道 Redis 的单个命令是原子性的（比如 get set mget mset），如果涉及到多个命令的时候，需要把多个命令作为一个不可分割的处理序列，就需要用到事务



# Lua脚本

# Redis为啥这么快

# 内存回收

# 持久化机制



