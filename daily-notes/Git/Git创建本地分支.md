# 如何在git上创建本地分支

1. 从已有的分支创建新的分支(如从master分支),创建一个ipps1229分支

```
Git checkout -b ipps1229


备注：也可以换成以下两步
git branch ipps1229
git checkout ipps1229 
```

2. 将ipps1229分支推送到远程

```
git push origin ipps1229:ipps1229

​ 冒号前面的ipps1229: 推送本地的ipps1229分支到远程origin
​ 冒号后面的ipps1229: 远程origin没有则会自动创建
```

3. 建立本地到远程仓库的连接，然后推送代码
```
$ git push --set-upstream origin ipps1229
```

4. 取消对 master 分支追踪
```
$ git branch --unset-upstream master
```
