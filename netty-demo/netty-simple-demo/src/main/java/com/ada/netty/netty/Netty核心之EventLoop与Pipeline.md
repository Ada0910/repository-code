# EventLoop与Pipeline

# EventLoop创建

第一阶段：创建阶段

（1）NioEventLoopGroup构造方法，由ThreadFactory创建、每个一个线程独立名字
默认创建CPU*2的个数
每个线程会有一个独立的名字

```
Runtime.getRuntime().availableProcessors() * 2)
```

（2）newChild()方法
给每一个EventLoop创建一个队列tailTasks

（3）会被放到线程队列里面去MpscQueue(线程池内部的执行队列)
无锁化串行队列 


（4）newChooser() 获取一个线程（封装成EventLoop对象）
执行阶段会调用EventLoop.exectue()

第二阶段：执行阶段
无锁化串行的执行流程（安全）
创建Selector 、NioEventLoop构造方法创建
（1） bind()
(2)调用EventLoop的execute()方法
判断线程是不是Netty创建的

（3）Selector关联，轮询

 select()方法
 processSelectedKeys()方法

第三阶段：新连接接入的处理



