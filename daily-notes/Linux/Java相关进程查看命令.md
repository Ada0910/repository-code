# 查看JAVA进程，找出PID

jps

# 实时查看和调整JVM配置参数

jinfo -flag name  PID

# 查看虚拟机性能统计信息

jstat -class PID  1000 10

# 查看垃圾回收信息

jstat -gc PID 1000 10

# 查看线程堆栈信息

jstack PID

# 打印出堆内存相关信息

jmap -heap PID



