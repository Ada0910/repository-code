# NioEventLoop

NioEventLoop聚合了多路复用器Selector，可以同时并发处理成百上千客户端Channel，由于读写操作都是非阻塞的，这就可以充分提升IO线程的运行效率，避免由于频繁IO阻塞导致的线程挂起

也就是一个线程池（异步非阻塞通信）

## 零拷贝

接收和发送ByteBuffer使用堆外直接内存进行Socket读写

## 内存池

## 高效的Reactor线程模型

## 无锁化串行设计理念

## 高效的并发编程

## 高性能的序列化框架

## 灵活的TCP参数配置能力

# Netty核心组件

new Channel()创建Channel对象-->init()初始化预设参数-->register()注册到Selector-->doBind()绑定端口

补充：
Provider获得一个Selector
把Server注册到Selector
显示的设置一个configureBlocking(true)
调用原生的bind()


## NioEventLoop核心逻辑



## Pipeline核心逻辑

## ByteBuf核心逻辑

# Netty常见的性能调优


