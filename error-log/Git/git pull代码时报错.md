# 问题

使用git pull的时候，遇到这样的错误

```
error: The following untracked working tree files would be overwritten by merge:
        [28]实现 strStr().java
        [53]最大子数组和.java
Please move or remove them before you merge.
Aborting
Updating 97f6926..9ac7183

```

# 分析

是由于本地的之前我新建了上面两个文件，然后在另一台电脑上修改之后提交上去了，两台电脑的文件不一致导致的，Git pull下来会覆盖本地的，所以提示说要将他们移走（缓存）在合并之前

# 解决

```
git clean -d -fx "[28]实现 strStr().java"
git clean -d -fx "[53]最大子数组和.java"


命令解释
x -----删除忽略文件已经对git来说不识别的文件
d -----删除未被添加到git的路径中的文件
f -----强制运行
```
