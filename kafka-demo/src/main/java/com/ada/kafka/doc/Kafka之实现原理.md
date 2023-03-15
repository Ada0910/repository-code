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

### JoinGroup过程

在rebalance之前，需要保证coordinator已经确定好，（试想一下，如果没有确定好，谁来协调）
整个rebalance分成两个步骤：Join 和Sync

(1)Join:
表示加入到consumer group中，在这一步中，所有的成员都会向coordinator发送joinGroup的请
求。一旦所有成员都发送了joinGroup请求，那么coordinator会选择一个consumer担任leader角色，
并把组成员信息和订阅信息发送消费者

leader选举算法比较简单，如果消费组内没有leader，那么第一个加入消费组的消费者就是消费者
leader，如果这个时候leader消费者退出了消费组，那么重新选举一个leader，这个选举很随意，类似
于随机算法

![image.png](./assets/1678850080306-image.png)

protocol_metadata: 序列化后的消费者的订阅信息
leader_id： 消费组中的消费者，coordinator会选择一个座位leader，对应的就是member_id
member_metadata 对应消费者的订阅信息
members：consumer group中全部的消费者的订阅信息
generation_id： 年代信息，类似于之前讲解zookeeper的时候的epoch是一样的，对于每一轮
rebalance，generation_id都会递增。主要用来保护consumer group。隔离无效的offset提交。也就
是上一轮的consumer成员无法提交offset到新的consumer group中。

每个消费者都可以设置自己的分区分配策略，对于消费组而言，会从各个消费者上报过来的分区分配策
略中选举一个彼此都赞同的策略来实现整体的分区分配，这个"赞同"的规则是，消费组内的各个消费者
会通过投票来决定
在joingroup阶段，每个consumer都会把自己支持的分区分配策略发送到coordinator
coordinator手机到所有消费者的分配策略，组成一个候选集
每个消费者需要从候选集里找出一个自己支持的策略，并且为这个策略投票
最终计算候选集中各个策略的选票数，票数最多的就是当前消费组的分配策略

(2)Sync:
完成分区分配之后，就进入了Synchronizing Group State阶段，主要逻辑是向GroupCoordinator发送
SyncGroupRequest请求，并且处理SyncGroupResponse响应，简单来说，就是leader将消费者对应
的partition分配方案同步给consumer group 中的所有consumer
![image.png](./assets/1678850220592-image.png)

每个消费者都会向coordinator发送syncgroup请求，不过只有leader节点会发送分配方案，其他消费者
只是打打酱油而已。当leader把方案发给coordinator以后，coordinator会把结果设置到
SyncGroupResponse中。这样所有成员都知道自己应该消费哪个分区。
Ø consumer group的分区分配方案是在客户端执行的！Kafka将这个权利下放给客户端主要是因为这
样做可以有更好的灵活性

### 总结

我们再来总结一下consumer group rebalance的过程
Ø 对于每个consumer group子集，都会在服务端对应一个GroupCoordinator进行管理，
GroupCoordinator会在zookeeper上添加watcher，当消费者加入或者退出consumer group时，会修
改zookeeper上保存的数据，从而触发GroupCoordinator开始Rebalance操作
（发生变化触发rebalance操作）

Ø 当消费者准备加入某个Consumer group或者GroupCoordinator发生故障转移时，消费者并不知道
GroupCoordinator的在网络中的位置，这个时候就需要确定GroupCoordinator，消费者会向集群中的
任意一个Broker节点发送ConsumerMetadataRequest请求，收到请求的broker会返回一个response
作为响应，其中包含管理当前ConsumerGroup的GroupCoordinator，
（确定GroupCoordinator，寻找协调者的位置）
Ø 消费者会根据broker的返回信息，连接到groupCoordinator，并且发送HeartbeatRequest，发送心
跳的目的是要确定GroupCoordinator这个消费者是正常在线的。当消费者在指定时间内没有发送
心跳请求，则GroupCoordinator会触发Rebalance操作。
（确定要重新选举）

Ø 发起join group请求，两种情况
如果GroupCoordinator返回的心跳包数据包含异常，说明GroupCoordinator因为前面说的几种
情况导致了Rebalance操作，那这个时候，consumer会发起join group请求
新加入到consumer group的consumer确定好了GroupCoordinator以后
消费者会向GroupCoordinator发起join group请求，GroupCoordinator会收集全部消费者信息之
后，来确认可用的消费者，并从中选取一个消费者成为group_leader。并把相应的信息（分区分
配策略、leader_id、…）封装成response返回给所有消费者，但是只有group leader会收到当前
consumer group中的所有消费者信息。当消费者确定自己是group leader以后，会根据消费者的
信息以及选定分区分配策略进行分区分配
（整合资源，开始选举）

接着进入Synchronizing Group State阶段，每个消费者会发送SyncGroupRequest请求到
GroupCoordinator，但是只有Group Leader的请求会存在分区分配结果，GroupCoordinator会
根据Group Leader的分区分配结果形成SyncGroupResponse返回给所有的Consumer。
consumer根据分配结果，执行相应的操作
（公示老大和分钱方案）
到这里为止，我们已经知道了消息的发送分区策略，以及消费者的分区消费策略和rebalance。对于应
用层面来说，还有一个最重要的东西没有讲解，就是offset，他类似一个游标，表示当前消费的消息的
位置。

# 如何保存消费端的消费位置

## 什么是offset

前面在讲解partition的时候，提到过offset， 每个topic可以划分多个分区（每个Topic至少有一个分
区），同一topic下的不同分区包含的消息是不同的。每个消息在被添加到分区时，都会被分配一个
offset（称之为偏移量），它是消息在此分区中的唯一编号，kafka通过offset保证消息在分区内的顺
序，offset的顺序不跨分区，即kafka只保证在同一个分区内的消息是有序的； 对于应用层的消费来
说，每次消费一个消息并且提交以后，会保存当前消费到的最近的一个offset。那么offset保存在哪
里
![image.png](./assets/1678851565881-image.png)

### offset在哪里维护

在kafka中，提供了一个consumer_offsets_* 的一个topic，把offset信息写入到这个topic中。
consumer_offsets——按保存了每个consumer group某一时刻提交的offset信息。
__consumer_offsets 默认有50个分区。

根据前面我们演示的案例，我们设置了一个KafkaConsumerDemo的groupid。首先我们需要找到这个
consumer_group保存在哪个分区中
properties.put(ConsumerConfig.GROUP_ID_CONFIG,"KafkaConsumerDemo");
计算公式
Math.abs(“groupid”.hashCode())%groupMetadataTopicPartitionCount ; 由于默认情况下
groupMetadataTopicPartitionCount有50个分区，计算得到的结果为:35, 意味着当前的
consumer_group的位移信息保存在__consumer_offsets的第35个分区
执行如下命令，可以查看当前consumer_goup中的offset位移提交的信息

```linux
kafka-console-consumer.sh --topic __consumer_offsets --partition 15 --
bootstrap-server 127.0.0.1:9092
--formatter
'kafka.coordinator.group.GroupMetadataManager$OffsetsMessageFormatter'
```

从输出结果中，我们就可以看到test这个topic的offset的位移日志

## 分区的副本机制

我们已经知道Kafka的每个topic都可以分为多个Partition，并且多个partition会均匀分布在集群的各个
节点下。虽然这种方式能够有效的对数据进行分片，但是对于每个partition来说，都是单点的，当其中
一个partition不可用的时候，那么这部分消息就没办法消费。所以kafka为了提高partition的可靠性而
提供了副本的概念（Replica）,通过副本机制来实现冗余备份。

每个分区可以有多个副本，并且在副本集合中会存在一个leader的副本，所有的读写请求都是由leader
副本来进行处理。剩余的其他副本都做为follower副本，follower副本会从leader副本同步消息日志。
这个有点类似zookeeper中leader和follower的概念，但是具体的时间方式还是有比较大的差异。所以
我们可以认为，副本集会存在一主多从的关系。

一般情况下，同一个分区的多个副本会被均匀分配到集群中的不同broker上，当leader副本所在的
broker出现故障后，可以重新选举新的leader副本继续对外提供服务。通过这样的副本机制来提高
kafka集群的可用性

### 创建一个带副本机制的topic

通过下面的命令去创建带2个副本的topic

```
sh kafka-topics.sh --create --zookeeper 127.0.0.1:2181 --replication-factor
3 --partitions 3 --topic secondTopic
```

我们可以在/tmp/kafka-log路径下看到对应topic的副本信息了。我们通过一个图形的方式来表达。
针对secondTopic这个topic的3个分区对应的3个副本
![image.png](./assets/1678862112182-image.png)

如何知道那个各个分区中对应的leader是谁呢
在zookeeper服务器上，通过如下命令去获取对应分区的信息, 比如下面这个是获取secondTopic第1个
分区的状态信息

```linux
get /brokers/topics/secondTopic/partitions/1/state
```

{"controller_epoch":12,"leader":0,"version":1,"leader_epoch":0,"isr":[0,1]}
或通过这个命令 sh kafka-topics.sh --zookeeper 192.168.13.106:2181 --describe --topic
test_partition

leader表示当前分区的leader是那个broker-id。下图中。绿色线条的表示该分区中的leader节点。其他
节点就为follower

![image.png](./assets/1678862446520-image.png)

## 副本的leader选举

Kafka提供了数据复制算法保证，如果leader副本所在的broker节点宕机或者出现故障，或者分区的
leader节点发生故障，这个时候怎么处理呢？
那么，kafka必须要保证从follower副本中选择一个新的leader副本。那么kafka是如何实现选举的呢？
要了解leader选举，我们需要了解几个概念

Kafka分区下有可能有很多个副本(replica)用于实现冗余，从而进一步实现高可用。副本根据角色的不同
可分为3类：
leader副本：响应clients端读写请求的副本

follower副本：被动地备份leader副本中的数据，不能响应clients端读写请求。

ISR副本：包含了leader副本和所有与leader副本保持同步的follower副本——如何判定是否与leader同
步后面会提到每个Kafka副本对象都有两个重要的属性：LEO和HW。注意是所有的副本，而不只是
leader副本。

LEO：即日志末端位移(log end offset)，记录了该副本底层日志(log)中下一条消息的位移值。注意是下
一条消息！也就是说，如果LEO=10，那么表示该副本保存了10条消息，位移值范围是[0, 9]。另外，
leader LEO和follower LEO的更新是有区别的。我们后面会详细说

HW：即上面提到的水位值。对于同一个副本对象而言，其HW值不会大于LEO值。小于等于HW值的所
有消息都被认为是“已备份”的（replicated）。同理，leader副本和follower副本的HW更新是有区别的
从生产者发出的 一 条消息首先会被写入分区的leader 副本，不过还需要等待ISR集合中的所有
follower副本都同步完之后才能被认为已经提交，之后才会更新分区的HW, 进而消费者可以消费
到这条消息

## 副本协同机制

刚刚提到了，消息的读写操作都只会由leader节点来接收和处理。follower副本只负责同步数据以及当
leader副本所在的broker挂了以后，会从follower副本中选取新的leader
写请求首先由Leader副本处理，之后follower副本会从leader上拉取写入的消息，这个过程会有一定的
延迟，导致follower副本中保存的消息略少于leader副本，但是只要没有超出阈值都可以容忍。但是如
果一个follower副本出现异常，比如宕机、网络断开等原因长时间没有同步到消息，那这个时候，
leader就会把它踢出去。kafka通过ISR集合来维护一个分区副本信息
![image.png](./assets/1678862503805-image.png)
一个新leader被选举并被接受客户端的消息成功写入。Kafka确保从同步副本列表中选举一个副本为
leader；leader负责维护和跟踪ISR(in-Sync replicas ， 副本同步队列)中所有follower滞后的状态。当
producer发送一条消息到broker后，leader写入消息并复制到所有follower。消息提交之后才被成功复
制到所有的同步副本

## ISR

ISR表示目前“可用且消息量与leader相差不多的副本集合，这是整个副本集合的一个子集”。怎么去理解
可用和相差不多这两个词呢？具体来说，ISR集合中的副本必须满足两个条件

1. 副本所在节点必须维持着与zookeeper的连接
2. 副本最后一条消息的offset与leader副本的最后一条消息的offset之间的差值不能超过指定的阈值
   (replica.lag.time.max.ms) replica.lag.time.max.ms：如果该follower在此时间间隔内一直没有追
   上过leader的所有消息，则该follower就会被剔除isr列表
3. ISR数据保存在Zookeeper的 /brokers/topics/<topic>/partitions/<partitionId>/state
   节点中
   follower副本把leader副本LEO之前的日志全部同步完成时，则认为follower副本已经追赶上了leader
   副本，这个时候会更新这个副本的lastCaughtUpTimeMs标识，kafk副本管理器会启动一个副本过期检
   查的定时任务，这个任务会定期检查当前时间与副本的lastCaughtUpTimeMs的差值是否大于参数
   replica.lag.time.max.ms 的值，如果大于，则会把这个副本踢出ISR集合
   
   ![image.png](./assets/1678862543829-image.png)
   















