
# git pull解释
- git pull命令的作用是

取回远程主机某个分支的更新，再与本地的指定分支合并。

- 一句话总结git pull和git fetch的区别：

```
git pull = git fetch + git merge
```

git fetch不会进行合并执行后需要手动执行git merge合并分支，而git pull拉取远程分之后直接与本地分支进行合并。更准确地说，git pull使用给定的参数运行git fetch，并调用git merge将检索到的分支头合并到当前分支中。


- 基本用法：

```
git pull <远程主机名> <远程分支名>:<本地分支名>
```

例如执行下面语句：

```
git pull origin master:brantest 

将远程主机origin的master分支拉取过来，与本地的brantest分支合并。
```


后面的冒号可以省略：

```
git pull origin master
表示将远程origin主机的master分支拉取过来和本地的当前分支进行合并。
```

上面的pull操作用fetch表示为：

```
git fetch origin master:brantest
git merge brantest
相比起来git fetch更安全一些，因为在merge前，我们可以查看更新情况，然后再决定是否合
```
# Git 命令大全
- 删除本地分支
```
$ git branch -d dev
```
- 删除远程分支
```
$ git push origin --delete dev
```

- git branch命令

```
git branch branchName(在本地创建一个命名为branchName的分支)
git branch 查看当前自己所在的分支
git branch -a 查看服务器的所有分支以及自己当前所在的分支
```

- 推送命令
```
git push origin branchName(把命名为branchName的本地分支推送到服务器)
```

- 切换

```
git checkout --track origin/branchName (切换为远程服务器上的命名为branchName的远程分支)

 如果你的搭档要把他本地的分支给关联到服务器上命名为branchName的远程分支，请执行以下操作，
git branch --set-upstream localBranchName origin/branchName  
（为本地分支添加一个对应的远程分支 与之相对应）->这行命令用来关联本地的分支与服务器上的分支
```



- Git 全局设置
```
git config --global user.name "well"
git config --global user.email "welllife3@163.com"
```


- 更新远程分支列表
```
git remote update origin --prune
```

- 查看所有分支
```
git branch -a
```

- 删除远程分支Chapater6
```
git push origin --delete Chapater6
```

- 删除本地分支 Chapater6
```
git branch -d  Chapater6
```

