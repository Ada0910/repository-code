# Docker进阶

# Image

image -> 由Dockerfile 创建而来

## Dockerfile文件

1.Dockerfile文件内容

```java
FROM  openjdk:8
MAINTAINER xwn
LABEL name = 'dockerfile-demo' vesion="1.0.0" 
COPY docker-demo-0.0.1-SNAPSHOT.jar dockerfile-demo.jar
CMD ["java","-jar","dockerfile-demo.jar"]
```

2. 构建

docker build -t my-docker-demo（镜像的名字） .

3. 运行
   docker run -d --name xxx(名字) -p 映射端口：内部端口 实例名

# 镜像仓库

## docker hub

(1)在docker机器上登录
docker login
(2)输入用户名和密码
(3)docker push itcrazy2018/test-docker-image
[注意镜像名称要和docker id一致，不然push不成功]
(4)给image重命名，并删除掉原来的
docker tag test-docker-image itcrazy2018/test-docker-image
docker rmi -f test-docker-image
(5)再次推送，刷新hub.docker.com后台，发现成功
(6)别人下载，并且运行
docker pull itcrazy2018/test-docker-image
docker run -d --name user01 -p 6661:8080 itcrazy2018/test-docker-image

## 阿里云

(1)登录到阿里云docker仓库
sudo docker login --username=itcrazy2016@163.com registry.cn-
hangzhou.aliyuncs.com
(2)输入密码
(3)创建命名空间，比如itcrazy2016
(4)给image打tag
sudo docker tag [ImageId] registry.cn-hangzhou.aliyuncs.com/itcrazy2016/test-
docker-image:v1.0
(5)推送镜像到docker阿里云仓库
sudo docker push registry.cn-hangzhou.aliyuncs.com/itcrazy2016/test-docker-
image:v1.0
(6)别人下载，并且运行
docker pull registry.cn-hangzhou.aliyuncs.com/itcrazy2016/test-docker-
image:v1.0
docker run -d --name user01 -p 6661:8080 registry.cn-
hangzhou.aliyuncs.com/itcrazy2016/test-docker-image:v1.0

## 搭建自己的仓库

(1)访问github上的harbor项目
https://github.com/goharbor/harbor
(2)下载版本，比如1.7.1
https://github.com/goharbor/harbor/releases
(3)找一台安装了docker-compose[这个后面的课程会讲解]，上传并解压
tar -zxvf xxx.tar.gz
(4)进入到harbor目录
修改harbor.cfg文件，主要是ip地址的修改成当前机器的ip地址
同时也可以看到Harbor的密码，默认是Harbor12345
(5)安装harbor，需要一些时间
sh install.sh
(6)浏览器访问，比如39.100.39.63，输入用户名和密码即可

# container到image

(1)拉取一个centos image
docker pull centos
(2)根据centos镜像创建出一个container
docker run -d -it --name my-centos centos
(3)进入my-centos容器中
docker exec -it my-centos bash
(4)输入vim命令
bash: vim: command not found
(5)我们要做的是
对该container进行修改，也就是安装一下vim命令，然后将其生成一个新的centos
(6)在centos的container中安装vim
yum install -y vim
(7)退出容器，将其生成一个新的centos，名称为"vim-centos-image"
docker commit my-centos vim-centos-image
(8)查看镜像列表，并且基于"vim-centos-image"创建新的容器
docker run -d -it --name my-vim-centos vim-centos-image
(9)进入到my-vim-centos容器中，检查vim命令是否存在
docker exec -it my-vim-centos bash
vim
结论 ：可以通过docker commit命令基于一个container重新生成一个image，但是一般得到image的
方式不建议这么做，不然image怎么来的就全然不知咯。

# container资源限制

查看资源情况 ：docker stats
（1）内存限制
--memory  Memory limit
如果不设置 --memory-swap，其大小和memory一样
docker run -d --memory 100M --name tomcat1 tomcat

（2） CPU限制
--cpu-shares  权重
docker run -d --cpu-shares 10 --name tomcat2 tomcat

（3）图形化资源监控
https://github.com/weaveworks/scope

sudo curl -L git.io/scope -o /usr/local/bin/scope
sudo chmod a+x /usr/local/bin/scope
scope launch 39.100.39.63

#停止scope
scope stop

#同时监控两台机器，在两台机器中分别执行如下命令
scope launch ip1 ip

## container常见操作

(1)根据镜像创建容器
docker run -d --name -p 9090:8080 my-tomcat tomcat
(2)查看运行中的container
docker ps
(3)查看所有的container[包含退出的]
docker ps -a
(4)删除container
docker rm containerid
docker rm -f $(docker ps -a)  删除所有container
(5)进入到一个container中
docker exec -it container bash
(6)根据container生成image
docker

## 底层技术支持

Container是一种轻量级的虚拟化技术，不用模拟硬件创建虚拟机。
Docker是基于Linux Kernel的Namespace、CGroups、UnionFileSystem等技术封装成的一种自
定义容器格式，从而提供一套虚拟运行环境。
(7)查看某个container的日志
docker logs container
(8)查看容器资源使用情况
docker stats
(9)查看容器详情信息
docker inspect container
(10)停止/启动容器
docker stop/start container
