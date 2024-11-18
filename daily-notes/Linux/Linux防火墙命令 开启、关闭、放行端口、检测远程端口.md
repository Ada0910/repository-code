作者：xwn

# 一、查看系统防火墙状态（如果返回 running 代表防火墙启动正常）

```text
 systemctl status firewalld
```

# 二、放行端口

```text
1. firewall-cmd --zone=public --add-port=80/tcp --permanent  #放行80端口
2. firewall-cmd --zone=public --add-port=443/tcp --permanent #放行443端口
3. 开放多个端口
 firewall-cmd --zone=public --add-port=80-85/tcp --permanent

 firewall-cmd --reload  #重新载入 返回 success 代表成功
 
4.查看 返回 yes 代表开启成功
 firewall-cmd --zone=public --query-port=80/tcp
```

# 三、关闭端口

```text
删除端口 返回 success 代表成功
1 firewall-cmd --zone=public --remove-port=80/tcp --permanent   #关闭80端口

2、重新载入 返回 success 代表成功
 firewall-cmd --reload

```

# 四、基本操作

```text
1、启动服务：systemctl start firewalld.service
2、关闭服务：systemctl stop firewalld.service
3、重启服务：systemctl restart firewalld.service
4、显示服务的状态：systemctl status firewalld.service
5、开机自动启动：systemctl enable firewalld.service
6、禁用开机自动启动：systemctl disable firewalld.service

7、查看版本： firewall-cmd --version
8、查看帮助： firewall-cmd --help
9、显示状态： firewall-cmd --state
10、查看所有打开的端口： firewall-cmd --zone=public --list-ports
11、更新防火墙规则： firewall-cmd --reload

12、查看区域信息: firewall-cmd --get-active-zones
13、查看指定接口所属区域： firewall-cmd --get-zone-of-interface=eth0
14、拒绝所有包：firewall-cmd --panic-on
15、取消拒绝状态： firewall-cmd --panic-off
16、查看是否拒绝： firewall-cmd --query-panic
```

# 五、Linux检测远程端口是否打开

方法一

```text
telnet 192.168.10.19 2375 #telnet +对方ip +端口号
```

方法二

```text
curl 192.168.10.19:2375
```

