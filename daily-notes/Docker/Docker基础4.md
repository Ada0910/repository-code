# Docker的持久化方案

## Volume

(1)创建mysql数据库的container
docker run -d --name mysql01 -e MYSQL_ROOT_PASSWORD=jack123 mysql

(2)查看volume
docker volume ls

(3)具体查看该volume
docker volume inspect
48507d0e7936f94eb984adf8177ec50fc6a7ecd8745ea0bc165ef485371589e8

(4)名字不好看，name太长，修改一下
"-v mysql01_volume:/var/lib/mysql"表示给上述的volume起一个能识别的名字

docker run -d --name mysql01 -v mysql01_volume:/var/lib/mysql -e
MYSQL_ROOT_PASSWORD=jack123 mysql

(5)查看volume
docker volume ls
docker volume inspect mysql01_volume

(6)真的能够持久化保存数据吗？不妨来做个实验

```java
#进入容器中
docker exec -it mysql01 bash

#登录mysql服务
mysql -uroot -pjack123

#创建测试库
create database db_test

#退出mysql服务，退出mysql container

#删除mysql容器
docker rm -f mysql01

#查看volume
docker volume ls

#发现volume还在
| information_schema |
| db_test    |
| mysql       |
| performance_schema |
| sys
```

## Bind Mounting

（1）创建一个tomcat容器
docker run -d --name tomcat01 -p 9090:8080 -v
/tmp/test:/usr/local/tomcat/webapps/test tomcat

（2）查看两个目录
centos：cd /tmp/test
tomcat容器：cd /usr/local/tomcat/webapps/test

（3）在centos的/tmp/test中新建1.html，并写一些内容

<p style="color:blue; font-size:20pt;">This is p!</p>

(4)进入tomcat01的对应目录查看，发现也有一个1.html，并且也有内容

(5)在centos7上访问该路径：curl localhost:9090/test/1.html

（6）在win浏览器中通过ip访问

# Docker实战

## MySQL高可用集群搭建

(1)拉取haproxy镜像
docker pull percona/percona-xtradb-cluster:5.7.21

02 复制pxc镜像(实则重命名)
docker tag percona/percona-xtradb-cluster:5.7.21 pxc

03 删除pxc原来的镜像
docker rmi percona/percona-xtradb-cluster:5.7.21

04 创建一个单独的网段，给mysql数据库集群使用
(1)docker network create --subnet=172.18.0.0/24 pxc-net
(2)docket network inspect pxc-net  [查看详情]
(3)docker network rm pxc-net    [删除]

05 创建和删除volume
创建：docker volume create --name v1
删除：docker volume rm v1
查看详情：docker volume inspect v1

06 创建单个PXC容器demo
[CLUSTER_NAME PXC集群名字]
 [XTRABACKUP_PASSWORD数据库同步需要用到的密码]
 
docker run -d -p 3301:3306
-v v1:/var/lib/mysql
-e MYSQL_ROOT_PASSWORD=jack123
-e CLUSTER_NAME=PXC
-e XTRABACKUP_PASSWORD=jack123
--privileged --name=node1 --net=pxc-net --ip 172.18.0.2
pxc

07 搭建PXC[MySQL]集群
(1)准备3个数据卷
docker volume create --name v1
docker volume create --name v2
docker volume create --name v3

(2)运行三个PXC容器
docker run -d -p 3301:3306 -v v1:/var/lib/mysql -e
MYSQL_ROOT_PASSWORD=jack123 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=jack123 -
-privileged --name=node1 --net=pxc-net --ip 172.18.0.2 pxc

[CLUSTER_JOIN将该数据库加入到某个节点上组成集群]

docker run -d -p 3302:3306 -v v2:/var/lib/mysql -e
MYSQL_ROOT_PASSWORD=jack123 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=jack123 -
e CLUSTER_JOIN=node1 --privileged --name=node2 --net=pxc-net --ip 172.18.0.3 pxc

docker run -d -p 3303:3306 -v v3:/var/lib/mysql -e
MYSQL_ROOT_PASSWORD=jack123 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=jack123 -
e CLUSTER_JOIN=node1 --privileged --name=node3 --net=pxc-net --ip 172.18.0.4 pxc


(3)MySQL工具连接测试
Jetbrains Datagrip










