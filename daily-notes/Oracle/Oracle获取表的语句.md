# Oracle获取表的常见SQL

## 概览

- 在Oracle中查看所有的表:
  `select * from tab/dba_tables/dba_objects/cat;`
- 看用户建立的表 :    --当前用户的表
  `select table_name from user_tables;`
- 所有用户的表
  `select table_name from all_tables;`
- 包括系统表
  `select table_name from dba_tables;`
- 可以查询出所有的用户表索引

`select * from user_indexes`

## 其他

1、查找表的所有索引（包括索引名，类型，构成列）：

```sql

select t.*, i.index_type
from user_ind_columns t, user_indexes i
where t.index_name = i.index_name
and t.table_name = i.table_name
and t.table_name = 要查询的表
```

2、查找表的主键（包括名称，构成列）：

```sql
select cu.*
from user_cons_columns cu, user_constraints au
where cu.constraint_name = au.constraint_name
and au.constraint_type = 'P'
and au.table_name = 要查询的表
```

3、查找表的唯一性约束（包括名称，构成列）：

```sql

select column_name
from user_cons_columns cu, user_constraints au
where cu.constraint_name = au.constraint_name
and au.constraint_type = 'U'
and au.table_name = 要查询的表
```

4、查找表的外键（包括名称，引用表的表名和对应的键名，下面是分成多步查询）：

```sql
select *
from user_constraints c
where c.constraint_type = 'R'
and c.table_name = 要查询的表查询外键约束的列名：
select * from user_cons_columns cl where cl.constraint_name = 外键名称查询引用表的键的列名：
select *
from user_cons_columns cl
where cl.constraint_name = 外键引用表的键名
```

5、查询表的所有列及其属性

```sql
select t.*, c.COMMENTS
from user_tab_columns t, user_col_comments c
where t.table_name = c.table_name
and t.column_name = c.column_name
and t.table_name = 要查询的表
```
