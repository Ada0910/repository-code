作者：谢伟宁

# 场景：**判断一个A列表是否为空，并返回一个Map<主键，对象>的列表,防止**`**key**`**重复**

前提

```text
(1)CostCenterTypeModel 实体类 
public class CostCenterTypeModel extends AbstractBaseModel {
    @TableId
    private String id;

   //省略其他属性
    .....

}

（2）实体类查询接口（示例）
List<CostCenterTypeModel> list= getPreviousCostCenterTypeData();

```

## 一般常见写法

```text
Map<String, CostCenterTypeModel> currentCostCenterMap =
 new HashMap<>();

 if (list!= null && list.size() > 0) {
    for (CostCenterTypeModel model : list) {
       currentCostCenterMap.put(model.getId(),model);
    }
}

返回currentCostCenterMap
```

## Java8处理（更优雅）

```text
Map<String, CostCenterTypeModel> currentCostCenterMap = Optional.ofNullable(list)
       .orElse(Collections.emptyList())  .stream().collect(Collectors.toMap(CostCenterTypeModel::getId, Function.identity(), (v1, v2) -> v1));

return:返回currentCostCenterMap


备注:Entity指具体实体类
(1)可以将ID和name作为key-value
Collectors.toMap(Entity::getId, Entity::getName)

(2)根据 id 和 对象自己 转成 Map 集合
Collectors.toMap(Entity::getId, Function.identity())

(3)第三个参数可以防止重复
Collectors.toMap(Entity::getId, Entity::getName, (k1, k2) -> k1

```



