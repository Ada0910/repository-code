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

# **如何处理所有的Replica不工作的情况**

在ISR中至少有一个follower时，Kafka可以确保已经commit的数据不丢失，但如果某个Partition的所有Replica都宕机了，就无法保证数据不丢失了

1. 等待ISR中的任一个Replica“活”过来，并且选它作为Leader
2. 选择第一个“活”过来的Replica（不一定是ISR中的）作为Leader

这就需要在可用性和一致性当中作出一个简单的折衷。

如果一定要等待ISR中的Replica“活”过来，那不可用的时间就可能会相对较长。而且如果ISR中的所有

Replica都无法“活”过来了，或者数据都丢失了，这个Partition将永远不可用。

选择第一个“活”过来的Replica作为Leader，而这个Replica不是ISR中的Replica，那即使它并不保证已经包含了所有已commit的消息，它也会成为Leader而作为consumer的数据源（前文有说明，所有读写都由Leader完成）。在我们课堂讲的版本中，使用的是第一种策略。

# **副本数据同步原理**

了解了副本的协同过程以后，还有一个最重要的机制，就是数据的同步过程。它需要解决

1. 怎么传播消息
2. 在向消息发送端返回ack之前需要保证多少个Replica已经接收到这个消息

#### **数据的处理过程是**

下图中，深红色部分表示test_replica分区的leader副本，另外两个节点上浅色部分表示follower副本

![image.png](./assets/1678872557804-image.png)

Producer在发布消息到某个Partition时，

先通过ZooKeeper找到该Partition的Leader get

/brokers/topics/<topic>/partitions/2/state ，然后无论该Topic的Replication  Factor为多少（也即该Partition有多少个Replica），Producer只将该消息发送到该Partition的Leader。   Leader会将该消息写入其本地Log。每个Follower都从Leader     pull数据。这种方式上，Follower 存储的数据顺序与Leader保持一致。
Follower在收到该消息并写入其Log后，向Leader发送ACK。

一旦Leader收到了ISR中的所有Replica的ACK，该消息就被认为已经commit了，Leader将增加

HW(HighWatermark)并且向Producer发送ACK。

**LEO** ：即日志末端位移(log end oﬀset)，记录了该副本底层日志(log)中下一条消息的位移值。注意是下一条消息！也就是说，如果LEO=10，那么表示该副本保存了10条消息，位移值范围是[0, 9]。另外，leader LEO和follower LEO的更新是有区别的。我们后面会详细说

**HW** ：即上面提到的水位值（Hight Water）。对于同一个副本对象而言，其HW值不会大于LEO值。小于等于HW值的所有消息都被认为是“已备份”的（replicated）。同理，leader副本和follower副本的HW更新是有区别的

通过下面这幅图来表达LEO、HW的含义，随着follower副本不断和leader副本进行数据同步，follower 副本的LEO会主键后移并且追赶到leader副本，这个追赶上的判断标准是当前副本的LEO是否大于或者等于leader副本的HW，这个追赶上也会使得被踢出的follower副本重新加入到ISR集合中。

另外，    假如说下图中的最右侧的follower副本被踢出ISR集合，也会导致这个分区的HW发生变化，变成了3
![image.png](./assets/1678872644899-image.png)

**初始状态**

初始状态下，leader和follower的HW和LEO都是0，leader副本会保存remote LEO，表示所有follower LEO，也会被初始化为0，这个时候，producer没有发送消息。follower会不断地个leader发送FETCH    请求，但是因为没有数据，这个请求会被leader寄存，当在指定的时间之后会强制完成请求，这个时间   配置是(replica.fetch.wait.max.ms)，如果在指定时间内producer有消息发送过来，那么kafka会唤醒fetch请求，让leader继续处理

数据的同步处理会分两种情况，这两种情况下处理方式是不一样的
![image.png](./assets/1678872670007-image.png)

第一种是leader处理完producer请求之后，follower发送一个fetch请求过来

第二种是follower阻塞在leader指定时间之内，leader副本收到producer的请求。

## **第一种情况**

#### **生产者发送一条消息**

leader处理完producer请求之后，follower发送一个fetch请求过来。状态图如下
![image.png](./assets/1678872689977-image.png)

leader副本收到请求以后，会做几件事情

1. 把消息追加到log文件，同时更新leader副本的LEO
2. 尝试更新leader HW值。这个时候由于follower副本还没有发送fetch请求，那么leader的remote LEO仍然是0。leader会比较自己的LEO以及remote  LEO的值发现最小值是0，与HW的值相同，所以不会更新HW

#### **follower fetch****消息**

follower 发送fetch请求，leader副本的处理逻辑是:
![image.png](./assets/1678872708631-image.png)

1. 读取log数据、更新remote LEO=0(follower还没有写入这条消息，这个值是根据follower的fetch

请求中的oﬀset来确定的)

2. 尝试更新HW，因为这个时候LEO和remoteLEO还是不一致，所以仍然是HW=0
3. 把消息内容和当前分区的HW值发送给follower副本

follower副本收到response以后

1. 将消息写入到本地log，同时更新follower的LEO
2. 更新follower HW，本地的LEO和leader返回的HW进行比较取小的值，所以仍然是0

第一次交互结束以后，HW仍然还是0，这个值会在下一次follower发起fetch请求时被更新
![image.png](./assets/1678872722979-image.png)

follower发第二次fetch请求，leader收到请求以后

1. 读取log数据
2. 更新remote LEO=1，因为这次fetch携带的oﬀset是1.
3. 更新当前分区的HW，这个时候leader LEO和remote LEO都是1，所以HW的值也更新为1
4. 把数据和当前分区的HW值返回给follower副本，这个时候如果没有数据，则返回为空
   follower副本收到response以后
5. 如果有数据则写本地日志，并且更新LEO
6. 更新follower的HW值

到目前为止，数据的同步就完成了，意味着消费端能够消费oﬀset=1这条消息。

## **第二种情况**

前面说过，由于leader副本暂时没有数据过来，所以follower的fetch会被阻塞，直到等待超时或者leader接收到新的数据。当leader收到请求以后会唤醒处于阻塞的fetch请求。处理过程基本上和前面说的一致

1. leader将消息写入本地日志，更新Leader的LEO
2. 唤醒follower的fetch请求
3. 更新HW

kafka使用HW和LEO的方式来实现副本数据的同步，本身是一个好的设计，但是在这个地方会存在一个      数据丢失的问题，当然这个丢失只出现在特定的背景下。我们回想一下，HW的值是在新的一轮FETCH     中才会被更新。我们分析下这个过程为什么会出现数据丢失

## **数据丢失的问题**

前提
min.insync.replicas=1 //设定ISR中的最小副本数是多少，默认值为1（在server.properties中配
置）， 并且acks参数设置为-1（表示需要所有副本确认）时，此参数才生效

表达的含义是，至少需要多少个副本同步才能表示消息是提交的，所以，当min.insync.replicas=1 的时候，一旦消息被写入leader端log即被认为是“已提交”，而延迟一轮FETCH RPC更新HW值的设计使得follower  HW值是异步延迟更新的，倘若在这个过程中leader发生变更，那么成为新leader的follower的HW值就有可能是过期的，使得clients端认为是成功提交的消息被删除。
![image.png](./assets/1678872750105-image.png)

**producer****的****ack**
acks配置表示producer发送消息到broker上以后的确认值。有三个可选项

0：表示producer不需要等待broker的消息确认。这个选项时延最小但同时风险最大（因为当server宕机时，数据将会丢失）。

1：表示producer只需要获得kafka集群中的leader节点确认即可，这个选择时延较小同时确保了leader节点确认接收成功。

all(-1)：需要ISR中所有的Replica给予接收确认，速度最慢，安全性最高，但是由于ISR可能会缩小到仅包含一个Replica，所以设置参数为all并不能一定避免数据丢失，

## **数据丢失的解决方案**

在kafka0.11.0.0版本之后，引入了一个leader  epoch来解决这个问题，所谓的leader  epoch实际上是一对值（epoch，oﬀset），epoch代表leader的版本号，从0开始递增，当leader发生过变更，epoch   就+1，而oﬀset则是对应这个epoch版本的leader写入第一条消息的oﬀset，比如

(0,0), (1,50) ,表示第一个leader从oﬀset=0开始写消息，一共写了50条。第二个leader版本号是1，从oﬀset=50开始写，这个信息会持久化在对应的分区的本地磁盘上，文件名是/tmp/kafka- log/topic/leader-epoch-checkpoint 。

leader broker中会保存这样一个缓存，并且定期写入到checkpoint文件中

当leader写log时它会尝试更新整个缓存: 如果这个leader首次写消息，则会在缓存中增加一个条目；否则就不做更新。而每次副本重新成为leader时会查询这部分缓存，获取出对应leader版本的oﬀset

我们基于同样的情况来分析，follower宕机并且恢复之后，有两种情况，如果这个时候leader副本没有     挂，也就是意味着没有发生leader选举，那么follower恢复之后并不会去截断自己的日志，而是先发送     一个OﬀsetsForLeaderEpochRequest请求给到leader副本，leader副本收到请求之后返回当前的        LEO。

如果follower副本的leaderEpoch和leader副本的epoch相同，leader的leo只可能大于或者等于follower副本的leo值，所以这个时候不会发生截断

如果follower副本和leader副本的epoch值不同，那么leader副本会查找follower副本传过来的   epoch+1在本地文件中存储的StartOﬀset返回给follower副本，也就是新leader副本的LEO。这样也避  免了数据丢失的问题

如果leader副本宕机了重新选举新的leader，那么原本的follower副本就会变成leader，意味着epoch 从0变成1，使得原本follower副本中LEO的值的到了保留。

## **Leader****副本的选举过程**

1. KafkaController会监听ZooKeeper的/brokers/ids节点路径，一旦发现有broker挂了，执行下面     的逻辑。这里暂时先不考虑KafkaController所在broker挂了的情况，KafkaController挂了，各个broker会重新leader选举出新的KafkaController
2. leader副本在该broker上的分区就要重新进行leader选举，目前的选举策略是

a) 优先从isr列表中选出第一个作为leader副本，这个叫优先副本，理想情况下有限副本就是该分    区的leader副本

b) 如果isr列表为空，则查看该topic的unclean.leader.election.enable配置。

unclean.leader.election.enable：为true则代表允许选用非isr列表的副本作为leader，那么此     时就意味着数据可能丢失，为
false的话，则表示不允许，直接抛出NoReplicaOnlineException异常，造成leader副本选举失

败。

c) 如果上述配置为true，则从其他副本中选出一个作为leader副本，并且isr列表只包含该leader 副本。一旦选举成功，则将选举后

的leader和isr和其他副本信息写入到该分区的对应的zk路径上。

# **消息的存储**

消息发送端发送消息到broker上以后，消息是如何持久化的呢？那么接下来去分析下消息的存储

首先我们需要了解的是，kafka是使用日志文件的方式来保存生产者和发送者的消息，每条消息都有一     个oﬀset值来表示它在分区中的偏移量。Kafka中存储的一般都是海量的消息数据，为了避免日志文件过      大，Log并不是直接对应在一个磁盘上的日志文件，而是对应磁盘上的一个目录，这个目录的命名规则    是<topic_name>_<partition_id>

## **消息的文件存储机制**

一个topic的多个partition在物理磁盘上的保存路径，路径保存在  /tmp/kafka-logs/topic_partition，包含日志文件、索引文件和时间索引文件

kafka是通过分段的方式将Log分为多个LogSegment，LogSegment是一个逻辑上的概念，一个LogSegment对应磁盘上的一个日志文件和一个索引文件，其中日志文件是用来记录消息的。索引文件      是用来保存消息的索引。那么这个LogSegment是什么呢？

## **LogSegment**

假设kafka以partition为最小存储单位，那么我们可以想象当kafka  producer不断发送消息，必然会引起partition文件的无线扩张，这样对于消息文件的维护以及被消费的消息的清理带来非常大的挑战，所   以kafka 以segment为单位又把partition进行细分。每个partition相当于一个巨型文件被平均分配到多个大小相等的segment数据文件中（每个segment文件中的消息不一定相等），这种特性方便已经被消费的消息的清理，提高磁盘的利用率。

log.segment.bytes=107370   (设置分段大小),默认是1gb，我们把这个值调小以后，可以看到日志分段的效果

抽取其中3个分段来进行分析
![image.png](./assets/1678872794859-image.png)

segment ﬁle由2大部分组成，分别为index ﬁle和data ﬁle，此2个文件一一对应，成对出现，后缀".index"和“.log”分别表示为segment索引文件、数据文件.

segment文件命名规则：partion全局的第一个segment从0开始，后续每个segment文件名为上一个segment文件最后一条消息的oﬀset值进行递增。数值最大为64位long大小，20位数字字符长度，没有数字用0填充

## **查看segment文件命名规则**

通过下面这条命令可以看到kafka消息日志的内容

```
sh kafka-run-class.sh kafka.tools.DumpLogSegments --files /tmp/kafka-logs/test-
0/00000000000000000000.log --print-data-log
```

假如第一个log文件的最后一个oﬀset为:5376,所以下一个segment的文件命名为: 00000000000000005376.log。对应的index为00000000000000005376.index

## **segment****中****index****和****log****的对应关系**

从所有分段中，找一个分段进行分析

为了提高查找消息的性能，为每一个日志文件添加2个索引索引文件：OﬀsetIndex 和TimeIndex，分别对应*.index*以及.timeindex, TimeIndex索引文件格式：它是映射时间戳和相对oﬀset

```
sh kafka-run-class.sh kafka.tools.DumpLogSegments --files /tmp/kafka-logs/test-
0/00000000000000000000.index --print-data-log
```

查看索引内容：
![image.png](./assets/1678872834032-image.png)

如图所示，index中存储了索引以及物理偏移量。    log存储了消息的内容。索引文件的元数据执行对应数据文件中message的物理偏移地址。举个简单的案例来说，以[4053,80899]为例，在log文件中，对应的是第4053条记录，物理偏移量（position）为80899. position是ByteBuﬀer的指针位置

## **在partition中如何通过oﬀset查找message**

查找的算法是

1. 根据oﬀset的值，查找segment段中的index索引文件。由于索引文件命名是以上一个文件的最后一个oﬀset进行命名的，所以，使用二分查找算法能够根据oﬀset快速定位到指定的索引文件。
2. 找到索引文件后，根据oﬀset进行定位，找到索引文件中的符合范围的索引。（kafka采用稀疏索     引的方式来提高查找性能）
3. 得到position以后，再到对应的log文件中，从position出开始查找oﬀset对应的消息，将每条消息的oﬀset与目标oﬀset进行比较，直到找到消息

比如说，我们要查找oﬀset=2490这条消息，那么先找到00000000000000000000.index, 然后找到[2487,49111]这个索引，再到log文件中，根据49111这个position开始查找，比较每条消息的oﬀset是否大于等于2490。最后查找到对应的消息以后返回

## **Log****文件的消息内容分析**

前面我们通过kafka提供的命令，可以查看二进制的日志文件信息，一条消息，会包含很多的字段。

```
offset: 5371 position: 102124 CreateTime: 1531477349286 isvalid: true keysize:
-1 valuesize: 12 magic: 2 compresscodec: NONE producerId: -1 producerEpoch: -1
sequence: -1 isTransactional: false headerKeys: [] payload: message_5371
```

oﬀset和position这两个前面已经讲过了、createTime表示创建时间、keysize和valuesize表示key和value的大小、compresscodec表示压缩编码、payload:表示消息的具体内容

## **日志的清除策略以及压缩策略**

**日志清除策略**

前面提到过，日志的分段存储，一方面能够减少单个文件内容的大小，另一方面，方便kafka进行日志    清理。日志的清理策略有两个

1. 根据消息的保留时间，当消息在kafka中保存的时间超过了指定的时间，就会触发清理过程
2. 根据topic存储的数据大小，当topic所占的日志文件大小大于一定的阀值，则可以开始删除最旧的    消息。kafka会启动一个后台线程，定期检查是否存在可以删除的消息

通过log.retention.bytes和log.retention.hours这两个参数来设置，当其中任意一个达到要求，都会执行删除。

默认的保留时间是：7天

### **日志压缩策略**

Kafka还提供了“日志压缩（Log Compaction）”功能，通过这个功能可以有效的减少日志文件的大小，缓解磁盘紧张的情况，在很多实际场景中，消息的key和value的值之间的对应关系是不断变化的，就像   数据库中的数据会不断被修改一样，消费者只关心key对应的最新的value。因此，我们可以开启kafka 的日志压缩功能，服务端会在后台启动启动Cleaner线程池，定期将相同的key进行合并，只保留最新的value值。日志的压缩原理是
![image.png](./assets/1678872981323-image.png)



# **磁盘存储的性能问题**

## **磁盘存储的性能优化**

我们现在大部分企业仍然用的是机械结构的磁盘，如果把消息以随机的方式写入到磁盘，那么磁盘首先  要做的就是寻址，也就是定位到数据所在的物理地址，在磁盘上就要找到对应的柱面、磁头以及对应的  扇区；这个过程相对内存来说会消耗大量时间，为了规避随机读写带来的时间消耗，kafka采用顺序写    的方式存储数据。即使是这样，但是频繁的I/O操作仍然会造成磁盘的性能瓶颈

## **零拷贝**

消息从发送到落地保存，broker维护的消息日志本身就是文件目录，每个文件都是二进制保存，生产者    和消费者使用相同的格式来处理。在消费者获取消息时，服务器先从硬盘读取数据到内存，然后把内存  中的数据原封不动的通过socket发送给消费者。虽然这个操作描述起来很简单，但实际上经历了很多步   骤。

操作系统将数据从磁盘读入到内核空间的页缓存应用程序将数据从内核空间读入到用户空间缓存中

▪ 应用程序将数据写回到内核空间到socket缓存中

▪ 操作系统将数据从socket缓冲区复制到网卡缓冲区，以便将数据经网络发出
![image.png](./assets/1678872995683-image.png)



通过“零拷贝”技术，可以去掉这些没必要的数据复制操作，同时也会减少上下文切换次数。现代的unix    操作系统提供一个优化的代码路径，用于将数据从页缓存传输到socket；在Linux中，是通过sendﬁle系统调用来完成的。Java提供了访问这个系统调用的方法：FileChannel.transferTo API

使用sendﬁle，只需要一次拷贝就行，允许操作系统将数据直接从页缓存发送到网络上。所以在这个优    化的路径中，只有最后一步将数据拷贝到网卡缓存中是需要的
![image.png](./assets/1678873007082-image.png)



## **页缓存**

页缓存是操作系统实现的一种主要的磁盘缓存，但凡设计到缓存的，基本都是为了提升i/o性能，所以页  缓存是用来减少磁盘I/O操作的。

磁盘高速缓存有两个重要因素：

第一，访问磁盘的速度要远低于访问内存的速度，若从处理器L1和L2高速缓存访问则速度更快。     第二，数据一旦被访问，就很有可能短时间内再次访问。正是由于基于访问内存比磁盘快的多，所  以磁盘的内存缓存将给系统存储性能带来质的飞越。

当一个进程准备读取磁盘上的文件内容时，操作系统会先查看待读取的数据所在的页(page)是否在页缓存(pagecache)中，如果存在（命中）则直接返回数据，    从而避免了对物理磁盘的I/0操作；如果没有命中，  则操作系统会向磁盘发起读取请求并将读取的数据页存入页缓存，  之后再将数据返回给进程。同样，如果一个进程需要将数据写入磁盘，那么操作系统也会检测数据对应的页是否在页缓存中，如果不存在，则会先在页缓存中添加相应的页，最后将数据写入对应的页。被修改过后的页也就变成了脏页，操作系统会在合适的时间把脏页中的数据写入磁盘，以保持数据的一致性

Kafka中大量使用了页缓存，这是Kafka实现高吞吐的重要因素之一。虽然消息都是先被写入页缓存，然后由操作系统负责具体的刷盘任务的，但在Kafka中同样提供了同步刷盘及间断性强制刷盘(fsync),
可以通过	和 log.flush.interval.ms 参数来控制。

同步刷盘能够保证消息的可靠性，避免因为宕机导致页缓存数据还未完成同步时造成的数据丢失。但是  实际使用上，我们没必要去考虑这样的因素以及这种问题带来的损失，消息可靠性可以由多副本来解     决，同步刷盘会带来性能的影响。刷盘的操作由操作系统去完成即可

# **Kafka****消息的可靠性**

没有一个中间件能够做到百分之百的完全可靠，可靠性更多的还是基于几个9的衡量指标，比如4个9、5     个9. 软件系统的可靠性只能够无限去接近100%，但不可能达到100%。所以kafka如何是实现最大可能的可靠性呢？

分区副本，  你可以创建更多的分区来提升可靠性，但是分区数过多也会带来性能上的开销，一般来说，3个副本就能满足对大部分场景的可靠性要求

acks，生产者发送消息的可靠性，也就是我要保证我这个消息一定是到了broker并且完成了多副     本的持久化，但这种要求也同样会带来性能上的开销。它有几个可选项

1 ，生产者把消息发送到leader副本，leader副本在成功写入到本地日志之后就告诉生产者消息提交成功，但是如果isr集合中的follower副本还没来得及同步leader副本的消息，leader挂了，就会造成消息丢失

-1     ，消息不仅仅写入到leader副本，并且被ISR集合中所有副本同步完成之后才告诉生产者已经提交成功，这个时候即使leader副本挂了也不会造成数据丢失。

0：表示producer不需要等待broker的消息确认。这个选项时延最小但同时风险最大（因为当server宕机时，数据将会丢失）。

保障消息到了broker之后，消费者也需要有一定的保证，因为消费者也可能出现某些问题导致消 息没有消费到

enable.auto.commit默认为true，也就是自动提交oﬀset，自动提交是批量执行的，有一个时间窗口，这种方式会带来重复提交或者消息丢失的问题，所以对于高可靠性要求的程序，要使用手动提  交。对于高可靠要求的应用来说，宁愿重复消费也不应该因为消费异常而导致消息丢失

