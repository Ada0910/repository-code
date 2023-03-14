# 消息中间件能干哈

解决分布式系统之间的消息传递问题，它能够屏蔽各种平台以及协议之间的特性，实现应用程序之间的协同

如，一个会员的注册模块可以改造成这样
![image.png](./assets/1678616699318-image.png)

## batch.size

生产者发送多个消息到broker上的同一个分区时，为了减少网络请求带来的性能开销，通过批量的方式
来提交消息，可以通过这个参数来控制批量提交的字节数大小，默认大小是16384byte,也就是16kb，
意味着当一批消息大小达到指定的batch.size的时候会统一发送

## linger.ms

Producer默认会把两次发送时间间隔内收集到的所有Requests进行一次聚合然后再发送，以此提高吞
吐量，而linger.ms就是为每次发送到broker的请求增加一些delay，以此来聚合更多的Message请求。
这个有点想TCP里面的Nagle算法，在TCP协议的传输中，为了减少大量小数据包的发送，采用了Nagle
算法，也就是基于小包的等-停协议

batch.size和linger.ms这两个参数是kafka性能优化的关键参数，很多同学会发现batch.size和
linger.ms这两者的作用是一样的，如果两个都配置了，那么怎么工作的呢？实际上，当二者都配
置的时候，只要满足其中一个要求，就会发送请求到broker上

# 一些基础配置分析

## group.id

consumer group是kafka提供的可扩展且具有容错性的消费者机制。既然是一个组，那么组内必然可以
有多个消费者或消费者实例(consumer instance)，它们共享一个公共的ID，即group ID。组内的所有
消费者协调在一起来消费订阅主题(subscribed topics)的所有分区(partition)。当然，每个分区只能由
同一个消费组内的一个consumer来消费.如下图所示，分别有三个消费者，属于两个不同的group，那
么对于firstTopic这个topic来说，这两个组的消费者都能同时消费这个topic中的消息，对于此事的架构
来说，这个firstTopic就类似于ActiveMQ中的topic概念。如右图所示，如果3个消费者都属于同一个
group，那么此事firstTopic就是一个Queue的概念

![image.png](./assets/1678795761107-image.png)

## enable.auto.commit

费者消费消息以后自动提交，只有当消息提交以后，该消息才不会被再次接收到，还可以配合
auto.commit.interval.ms控制自动提交的频率。
当然，我们也可以通过consumer.commitSync()的方式实现手动提交

## auto.offset.reset

这个参数是针对新的groupid中的消费者而言的，当有新groupid的消费者来消费指定的topic时，对于
该参数的配置，会有不同的语义
auto.offset.reset=latest情况下，新的消费者将会从其他消费者最后消费的offset处开始消费Topic下的
消息
auto.offset.reset= earliest情况下，新的消费者会从该topic最早的消息开始消费
auto.offset.reset=none情况下，新的消费者加入以后，由于之前不存在offset，则会直接抛出异常

## max.poll.records

此设置限制每次调用poll返回的消息数，这样可以更容易的预测每次poll间隔要处理的最大值。通过调
整此值，可以减少poll间隔

# 原理分析

## Topic

在kafka中，topic是一个存储消息的逻辑概念，可以认为是一个消息集合。每条消息发送到kafka集群的
消息都有一个类别。物理上来说，不同的topic的消息是分开存储的，
每个topic可以有多个生产者向它发送消息，也可以有多个消费者去消费其中的消息

![image.png](./assets/1678798271275-image.png)

## Partition

每个topic可以划分多个分区（每个Topic至少有一个分区），同一topic下的不同分区包含的消息是不同
的。每个消息在被添加到分区时，都会被分配一个offset（称之为偏移量），它是消息在此分区中的唯
一编号，kafka通过offset保证消息在分区内的顺序，offset的顺序不跨分区，即kafka只保证在同一个
分区内的消息是有序的

下图中，对于名字为test的topic，做了3个分区，分别是p0、p1、p2.
Ø 每一条消息发送到broker时，会根据partition的规则选择存储到哪一个partition。如果partition规则
设置合理，那么所有的消息会均匀的分布在不同的partition中，这样就有点类似数据库的分库分表的概
念，把数据做了分片处理

![image.png](./assets/1678798360660-image.png)

## Topic&Partition的存储

artition是以文件的形式存储在文件系统中，比如创建一个名为firstTopic的topic，其中有3个
partition，那么在kafka的数据目录（/tmp/kafka-log）中就有3个目录，firstTopic-0~3， 命名规则是
<topic_name>-<partition_id>

```
sh kafka-topics.sh --create --zookeeper 192.168.11.156:2181 --replication-factor
1 --partitions 3 --topic firstTopic
```



