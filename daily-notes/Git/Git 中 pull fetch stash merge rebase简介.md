# git pull和git fetch简介

* git pull和git fetch 的区别

  `git fetch`是将远程主机的最新内容拉到本地，用户在检查了以后决定是否合并到工作本机分支中

  `git pull` 则是将远程主机的最新内容拉下来后直接合并，即：`git pull = git fetch + git merge`，这样可能会产生冲突，需要手动解决

---

# git pull --rebase和git pull区别

git fetch + git merge FETCH_HEAD的缩写，所以默认情况下，git pull就是先fetch,然后执行merge操作，如果加-rebase参数，就是使用git rebase代替git merge 。

# **merge 和 rebase**

- merge 是合并的意思，rebase是复位基底的意思。
  现在我们有这样的两个分支,test和master，提交如下：

```
D---E test
    /
A---B---C---F master
```

在master执行 `git merge test`然后会得到如下结果：

```
D--------E
    /          \
A---B---C---F---G    test , master
```

在master执行 `git rebase test`,然后得到如下结果：

```
A---C---D---E---C `---F` test , master
```

可以看到merge操作会生成一个新的节点，之前提交分开显示。而rebase操作不会生成新的节点，是将两个分支融合成一个线性的操作。

通过上面可以看到，想要更好的提交树，使用rebase操作会更好一点，这样可以线性的看到每一次提交，并且没有增加提交节点。
在操作中。merge操作遇到冲突时候，当前merge不能继续下去。手动修改冲突内容后，add 修改，commit 就可以了
而rebase操作的话，会中断rebase，同时会提示去解决冲突。解决冲突后，将修改add后执行git rebase -continue继续操作，或者git rebase -skip忽略冲突。

---

# git stash 简介

- git stash 命令的作用就是将目前还不想提交的但是已经修改的内容进行保存至堆栈中，后续可以在某个分支上恢复出堆栈中的内容；
- git stash 作用的范围包括工作区和暂存区中的内容，没有提交的内容都会保存至堆栈中；例如突然线上出现 bug，我们需要先切换到 master 分支，但当前分支的代码没有提交，直接切换分支，会将当前分支的新增的代码也会增加到 master 分支，而代码又不能此时 commit ，于是这时候就可以使用 git stash
