# 自定义缓存路径
1.0.0版本开始支持以下方式自定义缓存路径，按照优先级从高到低分别为：

## 通过Java System Property apollo.cacheDir
- 可以通过Java的System Property apollo.cacheDir来指定
- 在Java程序启动脚本中，可以指定-Dapollo.cacheDir=/opt/data/some-cache-dir
如果是运行jar文件，需要注意格式是java -Dapollo.cacheDir=/opt/data/some-cache-dir -jar xxx.jar
- 也可以通过程序指定，如System.setProperty("apollo.cacheDir", "/opt/data/some-cache-dir");

## 通过Spring Boot的配置文件
- 可以在Spring Boot的application.properties或bootstrap.properties中指定apollo.cacheDir=/opt/data/some-cache-dir

## 通过操作系统的System EnvironmentAPOLLO_CACHEDIR
- 可以通过操作系统的System Environment APOLLO_CACHEDIR来指定
- 注意key为全大写，且中间是_分隔

## 通过server.properties配置文件
- 可以在server.properties配置文件中指定apollo.cacheDir=/opt/data/some-cache-dir
- 对于Mac/Linux，文件位置为/opt/settings/server.properties
- 对于Windows，文件位置为C:\opt\settings\server.properties


参考链接：https://www.cnblogs.com/niejunlei/p/12502020.html