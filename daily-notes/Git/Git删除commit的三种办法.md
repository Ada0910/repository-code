# 删除commit的三种办法
删除 commit 的三种方法
# 删除文件
如果需要删除的 commit 是一个或多个文件，可以进行以下操作。

- 被提交到仓库的某个文件需要删除，可以使用 git rm 命令：

```
1. git rm <file> // 从工作区和暂存区删除某个文件
2. git commit -m "" // 再次提交到仓库
```
- 如果只想从暂存区删除文件，本地工作区不做出改变，可以：

```
1. git rm --cached <file>
```
如果在工作区不小心删错了某个文件，可以用 git checkout 将暂存区的文件覆盖工作区的文件，从而把误删的文件恢复：
```
1. git checkout -- <file>
```
用 git rm 删除文件，同时还会将这个删除操作记录下来；
用 rm 删除文件，删除的仅仅是本地物理文件，没有将其从 git 的记录中剔除。
git add 和 git rm 有相似的功能，
但 git add 仅能记录添加、改动的动作，删除的动作需靠 git rm 来完成。
## GitHub 删除某次 commit
如果需要删除的不只是某个文件，而是交错的代码，那么有以下三种方法可以删除 commit 。

## git reset
```
git reset ：回滚到某次提交。
git reset --soft：此次提交之后的修改会被退回到暂存区。
git reset --hard：此次提交之后的修改不做任何保留，git status 查看工作区是没有记录的。
```
- 回滚代码
如果需要删除的 commit 是最新的，那么可以通过 git reset 命令将代码回滚到之前某次提交的状态，但一定要将现有的代码做好备份，否则回滚之后这些变动都会消失。具体操作如下：

```
1. git log // 查询要回滚的 commit_id
2. git reset --hard commit_id // HEAD 就会指向此次的提交记录
3. git push origin HEAD --force // 强制推送到远端
```
误删恢复
如果回滚代码之后发现复制错了 commit_id，或者误删了某次 commit 记录，也可以通过下方代码恢复：
```
1. git relog // 复制要恢复操作的前面的 hash 值
2. git reset --hard hash // 将 hash 换成要恢复的历史记录的 hash 值
注意：删除中间某次提交时最好不要用 git reset 回退远程库，因为之后其他人提交代码时用 git pull 也会把自己的本地仓库回退到之前的版本，容易出现差错进而增加不必要的工作量。
```
## git rebase
git rebase：当两个分支不在一条线上，需要执行 merge 操作时使用该命令

- 撤销提交
如果中间的某次 commit 需要删除，可以通过 git rebase 命令实现，方法如下：
```
1. git log // 查找要删除的前一次提交的 commit_id
2. git rebase -i commit_id // 将 commit_id 替换成复制的值
3. 进入 Vim 编辑模式，将要删除的 commit 前面的 `pick` 改成 `drop`
4. 保存并退出 Vim
```
这样就完成了。

- 解决冲突
该命令执行时极有可能出现 reabase 冲突，可以通过以下方法解决

```
1. git diff // 查看冲突内容
2. // 手动解决冲突（冲突位置已在文件中标明）
3. git add <file> 或 git add -A // 添加
4. git rebase --continue // 继续 rebase
5. // 若还在 rebase 状态，则重复 2、3、4，直至 rebase 完成出现 applying 字样
6. git push
```
## git revert
```
git revert：放弃某次提交。
git revert 之前的提交仍会保留在 git log 中，而此次撤销会做为一次新的提交。
git revert -m：用于对 merge 节点的操作，-m 指定具体某个提交点。
```
- 撤销提交
要撤销中间某次提交时，使用 git revert 也是一个很好的选择：

```
1. git log // 查找需要撤销的 commit_id
2. git revert commit_id  // 撤销这次提交
```
撤销 merge 节点提交
如果这次提交是 merge 节点的话，则需要加上 -m 指令：
```
1. git revert commit_id -m 1 // 第一个提交点
2. // 手动解决冲突
3. git add -A
4. git commit -m ""
5. git revert commit_id -m 2 // 第二个提交点
6. // 重复 2，3，4
7. git push
```