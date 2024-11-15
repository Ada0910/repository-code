作者：谢伟宁

```text
--查看被锁的表
select b.owner,b.object_name,a.session_id,a.locked_mode from v$locked_object a,dba_objects b where b.object_id = a.object_id;

-- 查看那个死锁进程
SELECT sid, serial#, username, osuser FROM v$session where username = 'COST';

-- 结束进程 
alter system kill session'sid, serial#';


-- 查看索引
select index_name,index_type
from dba_indexes
where owner = 'COST' and index_name like 'IDX%';
```

