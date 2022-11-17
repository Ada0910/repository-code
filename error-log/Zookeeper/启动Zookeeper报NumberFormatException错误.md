#  环境：win10+Idea2021+Zookeeper-3.4.12
# 问题描述
 在zk的目录下，启动zkServer.cmd，然后在命令行查看  ./zkServer.cmd status，出现了一下的错误

# 现象
```
D:\ZooKeeper\zookeeper-3.4.12\bin>call "C:\JDK1.8\jdk1.8"\bin\java "-Dzookeeper.log.dir=D:\ZooKeeper\zookeeper-3.4.12\bin\.." "-Dzookeeper.root.logger=INFO,CONSOLE" -cp "D:\ZooKeeper\zookeeper-3.4.12\bin\..\build\classes;D:\ZooKeeper\zookeeper-3.4.12\bin\..\build\li
b\*;D:\ZooKeeper\zookeeper-3.4.12\bin\..\*;D:\ZooKeeper\zookeeper-3.4.12\bin\..\lib\*;D:\ZooKeeper\zookeeper-3.4.12\bin\..\conf" org.apache.zookeeper.server.quorum.QuorumPeerMain "D:\ZooKeeper\zookeeper-3.4.12\bin\..\conf\zoo.cfg" status 
2022-03-14 23:02:21,815 [myid:] - INFO  [main:DatadirCleanupManager@78] - autopurge.snapRetainCount set to 3
2022-03-14 23:02:21,818 [myid:] - INFO  [main:DatadirCleanupManager@79] - autopurge.purgeInterval set to 0
2022-03-14 23:02:21,818 [myid:] - INFO  [main:DatadirCleanupManager@101] - Purge task is not scheduled.
2022-03-14 23:02:21,819 [myid:] - WARN  [main:QuorumPeerMain@116] - Either no config or no quorum defined in config, running  in standalone mode
2022-03-14 23:02:21,897 [myid:] - ERROR [main:ZooKeeperServerMain@57] - Invalid arguments, exiting abnormally
java.lang.NumberFormatException: For input string: "D:\ZooKeeper\zookeeper-3.4.12\bin\..\conf\zoo.cfg"
        at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
        at java.lang.Integer.parseInt(Integer.java:580)
        at java.lang.Integer.parseInt(Integer.java:615)
        at org.apache.zookeeper.server.ServerConfig.parse(ServerConfig.java:61)
        at org.apache.zookeeper.server.ZooKeeperServerMain.initializeAndRun(ZooKeeperServerMain.java:86)
        at org.apache.zookeeper.server.ZooKeeperServerMain.main(ZooKeeperServerMain.java:55)
        at org.apache.zookeeper.server.quorum.QuorumPeerMain.initializeAndRun(QuorumPeerMain.java:119)
        at org.apache.zookeeper.server.quorum.QuorumPeerMain.main(QuorumPeerMain.java:81)
2022-03-14 23:02:21,903 [myid:] - INFO  [main:ZooKeeperServerMain@58] - Usage: ZooKeeperServerMain configfile | port datadir [ticktime] [maxcnxns]
Usage: ZooKeeperServerMain configfile | port datadir [ticktime] [maxcnxns]

```

# 解决方式
直接在bin目录下双击zkcli.cmd，连接成功，目前用命令行连接不上