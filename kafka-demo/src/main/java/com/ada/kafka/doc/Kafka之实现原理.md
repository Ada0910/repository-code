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

# 消息分发

## kafka消息分发策略

消息是kafka中最基本的数据单元，在kafka中，一条消息由key、value两部分构成，在发送一条消息
时，我们可以指定这个key，那么producer会根据key和partition机制来判断当前这条消息应该发送并
存储到哪个partition中。我们可以根据需要进行扩展producer的partition机制

## 消息默认的分发机制

默认情况下，kafka采用的是hash取模的分区算法。如果Key为null，则会随机分配一个分区。这个随机
是在这个参数”metadata.max.age.ms”的时间范围内随机选择一个。对于这个时间段内，如果key为
null，则只会发送到唯一的分区。这个值值哦默认情况下是10分钟更新一次。
关于Metadata，这个之前没讲过，简单理解就是Topic/Partition和broker的映射关系，每一个topic的
每一个partition，需要知道对应的broker列表是什么，leader是谁、follower是谁。这些信息都是存储
在Metadata这个类里面

## 消费端如何消费指定的分区

```
/消费指定分区的时候，不需要再订阅
//kafkaConsumer.subscribe(Collections.singletonList(topic));
//消费指定的分区
TopicPartition topicPartition=new TopicPartition(topic,0);
kafkaConsumer.assign(Arrays.asList(topicPartition));
```

# 消息的消费原理

## kafka消息消费原理演示

在实际生产过程中，每个topic都会有多个partitions，多个partitions的好处在于，一方面能够对
broker上的数据进行分片有效减少了消息的容量从而提升io性能。另外一方面，为了提高消费端的消费
能力，一般会通过多个consumer去消费同一个topic ，也就是消费端的负载均衡机制，也就是我们接下
来要了解的，在多个partition以及多个consumer的情况下，消费者是如何消费消息的
同时，在上一节课，我们讲了，kafka存在consumer group的概念，也就是group.id一样的
consumer，这些consumer属于一个consumer group，组内的所有消费者协调在一起来消费订阅主题
的所有分区。当然每一个分区只能由同一个消费组内的consumer来消费，那么同一个consumer
group里面的consumer是怎么去分配该消费哪个分区里的数据的呢？如下图所示，3个分区，3个消费
者，那么哪个消费者消分哪个分区？

![image.png](./assets/1678810049701-image.png)

## consumer和partition的数量建议

1. 如果consumer比partition多，是浪费，因为kafka的设计是在一个partition上是不允许并发的，
   所以consumer数不要大于partition数
2. 如果consumer比partition少，一个consumer会对应于多个partitions，这里主要合理分配
   consumer数和partition数，否则会导致partition里面的数据被取的不均匀。最好partiton数目是
   consumer数目的整数倍，所以partition数目很重要，比如取24，就很容易设定consumer数目
3. 如果consumer从多个partition读到数据，不保证数据间的顺序性，kafka只保证在一个partition
   上数据是有序的，但多个partition，根据你读的顺序会有不同
4. 增减consumer，broker，partition会导致rebalance，所以rebalance后consumer对应的
   partition会发生变化

## 什么时候会触发这个策略呢

出现以下几种情况时，kafka会进行一次分区分配操作，也就是kafka consumer的rebalance

1. 同一个consumer group内新增了消费者
2. 消费者离开当前所属的consumer group，比如主动停机或者宕机
3. topic新增了分区（也就是分区数量发生了变化）
   kafka consuemr的rebalance机制规定了一个consumer group下的所有consumer如何达成一致来分
   配订阅topic的每个分区。而具体如何执行分区策略，就是前面提到过的两种内置的分区策略。而kafka
   对于分配策略这块，提供了可插拔的实现方式， 也就是说，除了这两种之外，我们还可以创建自己的分
   配机制

## 什么是分区分配策略

通过前面的案例演示，我们应该能猜到，同一个group中的消费者对于一个topic中的多个partition，存
在一定的分区分配策略。
在kafka中，存在三种分区分配策略，一种是Range(默认)、 另一种是RoundRobin（轮询）、
StickyAssignor(粘性)。 在消费端中的ConsumerConfig中，通过这个属性来指定分区分配策略

```java
public static final String PARTITION_ASSIGNMENT_STRATEGY_CONFIG =
"partition.assignment.strategy";
```

### RangeAssignor（范围分区）

假设n = 分区数／消费者数量
m= 分区数％消费者数量
那么前m个消费者每个分配n+l个分区，后面的（消费者数量-m)个消费者每个分配n个分区

假设我们有10个分区，3个消费者，排完序的分区将会是0, 1, 2, 3, 4, 5, 6, 7, 8, 9；消费者线程排完序将
会是C1-0, C2-0, C3-0。然后将partitions的个数除于消费者线程的总数来决定每个消费者线程消费几个
分区。如果除不尽，那么前面几个消费者线程将会多消费一个分区。在我们的例子里面，我们有10个分
区，3个消费者线程， 10 / 3 = 3，而且除不尽，那么消费者线程 C1-0 将会多消费一个分区
的结果看起来是这样的：
C1-0 将消费 0, 1, 2, 3 分区
C2-0 将消费 4, 5, 6 分区
C3-0 将消费 7, 8, 9 分区
假如我们有11个分区，那么最后分区分配的结果看起来是这样的：
C1-0 将消费 0, 1, 2, 3 分区
C2-0 将消费 4, 5, 6, 7 分区
C3-0 将消费 8, 9, 10 分区
假如我们有2个主题(T1和T2)，分别有10个分区，那么最后分区分配的结果看起来是这样的：
C1-0 将消费 T1主题的 0, 1, 2, 3 分区以及 T2主题的 0, 1, 2, 3分区
C2-0 将消费 T1主题的 4, 5, 6 分区以及 T2主题的 4, 5, 6分区
C3-0 将消费 T1主题的 7, 8, 9 分区以及 T2主题的 7, 8, 9分区
可以看出，C1-0 消费者线程比其他消费者线程多消费了2个分区，这就是Range strategy的一个很明
显的弊端

### RoundRobinAssignor（轮询分区）

轮询分区策略是把所有partition和所有consumer线程都列出来，然后按照hashcode进行排序。最后通
过轮询算法分配partition给消费线程。如果所有consumer实例的订阅是相同的，那么partition会均匀
分布。
在我们的例子里面，假如按照 hashCode 排序完的topic-partitions组依次为T1-5, T1-3, T1-0, T1-8, T1-
2, T1-1, T1-4, T1-7, T1-6, T1-9，我们的消费者线程排序为C1-0, C1-1, C2-0, C2-1，最后分区分配的结果
为：
C1-0 将消费 T1-5, T1-2, T1-6 分区；
C1-1 将消费 T1-3, T1-1, T1-9 分区；
C2-0 将消费 T1-0, T1-4 分区；
C2-1 将消费 T1-8, T1-7 分区；
使用轮询分区策略必须满足两个条件

1. 每个主题的消费者实例具有相同数量的流
2. 每个消费者订阅的主题必须是相同的

### StrickyAssignor 分配策略

假设消费组有3个消费者：C0,C1,C2，它们分别订阅了4个Topic(t0,t1,t2,t3),并且每个主题有两个分
区(p0,p1),也就是说，整个消费组订阅了8个分区：tOpO 、 tOpl 、 tlpO 、 tlpl 、 t2p0 、
t2pl 、t3p0 、 t3pl
那么最终的分配场景结果为
CO: tOpO、tlpl 、 t3p0
Cl: tOpl、t2p0 、 t3pl
C2: tlpO、t2pl
这种分配方式有点类似于轮询策略，但实际上并不是，因为假设这个时候，C1这个消费者挂了，就势必会造成
重新分区（reblance），如果是轮询，那么结果应该是
CO: tOpO、tlpO、t2p0、t3p0
C2: tOpl、tlpl、t2pl、t3pl
然后，strickyAssignor它是一种粘滞策略，所以它会满足`分区的分配尽可能和上次分配保持相同`，所以
分配结果应该是
消费者CO: tOpO、tlpl 、 t3p0、t2p0
消费者C2: tlpO、t2pl、tOpl、t3pl
也就是说，C0和C2保留了上一次是的分配结果，并且把原来C1的分区分配给了C0和C2。 这种策略的好处是
使得分区发生变化时，由于分区的“粘性，减少了不必要的分区移动

### 谁来执行Rebalance以及管理consumer的group呢？

Kafka提供了一个角色：
coordinator来执行对于consumer group的管理，当consumer group的第一个consumer启动的时
候，它会去和kafka server确定谁是它们组的coordinator。之后该group内的所有成员都会和该
coordinator进行协调通信

### 如何确定coordinator

consumer group如何确定自己的coordinator是谁呢, 消费者向kafka集群中的任意一个broker发送一个
GroupCoordinatorRequest请求，服务端会返回一个负载最小的broker节点的id，并将该broker设置
为coordinator



