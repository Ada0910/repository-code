# git如何合并分支
- 假如我们现在在dev分支上，刚开发完项目，执行了下列命令：
```
git  add .
git  commit -m '提交的备注信息'
git  push -u origin dev
```


# 想将dev分支合并到master分支，操作如下：
1、首先切换到master分支上
```
git  checkout master
```
2、如果是多人开发的话 需要把远程master上的代码pull下来
```
git pull origin master
//如果是自己一个开发就没有必要了，为了保险期间还是pull
```
3、然后我们把dev分支的代码合并到master上
```
git  merge dev
```
4、然后查看状态及执行提交命令
```
git status

On branch master
Your branch is ahead of 'origin/master' by 12 commits.
  (use "git push" to publish your local commits)
nothing to commit, working tree clean
```

//上面的意思就是你有12个commit，需要push到远程master上 
> 最后执行下面提交命令
```
git push origin master
```

