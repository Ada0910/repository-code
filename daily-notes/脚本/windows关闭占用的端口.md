# windows关闭占用的端口

- 占用查询端口的pid查询

```
netstat -ano|findstr "9999"
```

- 关闭对应pid

```
taskkill  -F -PID 8960
-F 强制关闭
```

![](vx_images/3093911220161.png)
