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








