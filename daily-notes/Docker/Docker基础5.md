# Docker传统方式实现

## 获取python的镜像

> (1)创建文件夹

```shell
mkdir -p /tmp/python
cd /tmp/python
```

(2)创建app.py文件，写业务内容

```python
import time

import redis
from flask import Flask

app = Flask(__name__)
cache = redis.Redis(host='redis', port=6379)

def get_hit_count():
    retries = 5
    while True:
        try:
            return cache.incr('hits')
        except redis.exceptions.ConnectionError as exc:
            if retries == 0:
                raise exc
            retries -= 1
            time.sleep(0.5)

@app.route('/')
def hello():
    count = get_hit_count()
    return 'Hello World! I have been seen {} times.\n'.format(count)
```

(3)新建requirements.txt文件

```
flask
redis
```

> (4)编写Dockerfile

```dockerfile
FROM python:3.7-alpine
WORKDIR /code
ENV FLASK_APP app.py
ENV FLASK_RUN_HOST 0.0.0.0
RUN apk add --no-cache gcc musl-dev linux-headers
COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt
COPY . .
CMD ["flask", "run"]
```

> (5)根据Dockerfile生成image

```
docker build -t python-app-image .
```

> (6)查看images：docker images

```
python-app-image latest 7e1d81f366b7 3 minutes ago  213MB
```

## 获取Redis的镜像


```
docker pull redis:alpine
```

## 创建两个container

> (1)创建网络

```
docker network ls
docker network create --subnet=172.20.0.0/24 app-net
```

> (1)创建python程序的container，并指定网段和端口

```
docker run -d --name web -p 5000:5000 --network app-net python-app-image
```

> (2)创建redis的container，并指定网段

```
docker run -d --name redis --network app-net redis:alpine
```

### 6.2.4 访问测试

ip[centos]:5000

# docker compose

## 简介

> `官网`：https://docs.docker.com/compose/
> 
> ```
> Compose is a tool for defining and running multi-container Docker applications. With Compose, you use a YAML file to configure your application’s services. Then, with a single command, you create and start all the services from your configuration.
> ```

## 安装

Linux环境中需要单独安装

> `官网`：https://docs.docker.com/compose/install/

```
sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```

```
sudo chmod +x /usr/local/bin/docker-compose
```

## 前期准备

参考上面的python文件

需要删除上一步骤的网络

```
docker network rm app-net
docker volume rm -f $(docker volume ls)
docker images
docker rmi -f xxxx
```

## 编写docker-compose.yaml文件

```yaml
version: '3'
services: # 表示我要定义一些container了
  web:    # 一个service 就是docker中原来的container名字
    build: . # 这个点表示当前目录的Dockerfile进行构建
    ports:
      - "5000:5000"
    networks:
      - app-net

  redis:
    image: "redis:alpine"
    networks:
      - app-net

networks: # docker network create --name app-net
  app-net:
    driver: bridge
```

> (1)通过docker compose创建容器

```
docker-compose up -d
或者  docker-compose -f 文件名 up -d
```

(2)访问测试

















