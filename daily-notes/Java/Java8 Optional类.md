作者：谢伟宁

使用Optional类使得为空更判断更加优雅

## 需求场景**：现在获取这个人买房的城市**

例如：Person对象里定义了House对象，House对象里定义了Address对象

```text
public class Person {
    private String name;
    private int age;
    private House house;
    public House getHouse() {
        return house;
    }
}
class House {
    private long price;
    private Address address;
    public Address getAddress() {
        return address;
    }
}
class Address {
    private String country;
    private String city;
    public String getCity() {
        return city;
    }
}

```

## 一般写法

```text
public String getCity() {
    String city = new Person().getHouse().getAddress().getCity();
    return city;
}

但可能会有空指针的情况，比如这个人没房子，则House对象为null等情况发生，从而造成空指针问题

经过改造后的代码如下：也是我们一般的写法


public String getCity(Person person) {
    String city = "unknown";
    if (person == null) {
      return city; 
    }
    House house = person.getHouse();
    if (house == null) {
        return city;
    }
    Address address = house.getAddress();
    if (address == null) {
        return city;
    }
    return address.getCity();
}

这里的代码就相当难维护和难看了
```

## Java8 Optional处理 

```text
public String getCityUsingOptional(Person person) {
    String city = Optional.ofNullable(person)
            .map(Person::getHouse)
            .map(House::getAddress)
            .map(Address::getCity).orElse("Unknown city");
    return city;
}
Java8的Optional类就用一句话处理，更加优雅舒适
```

## 其他

| 方法                                       | 说明                                         |
| ---------------------------------------- | ------------------------------------------ |
| Optional.of ()                           | 创建Optional对象包装对象不为null（否则空指针异常）            |
| Optional.ofNullable ()                   | 包装对象可为null                                 |
| Optional.empty ()                        | 包装对象为null                                  |
| isPresent ()                             | 包装对象值是否存在（不要使用if当作传统null判断使用，违背设计初衷）       |
| get ()                                   | 获取包装对象的值（如果为null，报错NoSuchElementException） |
| orElse (T other)                         | 获取包装对象的值，有值就返回，为null返回传入的默认值（类型和包装对象的类型一样） |
| orElseGet (Supplier other)               | 获取包装对象的值，有值就返回，为null返回函数的值                 |
| orElseThrow (Supplier exceptionSupplier) | 获取包装对象的值，有值就返回，为null返回一个Throwable对象        |
| ifPresent (Consumer consumer)            | 如果包装对象的值不为null，对包装对象进行消费                   |
| filter (Predicate predicate)             | 符合判断就返回Optional本身，否则返回包装对象值为null的Optional  |
| map (Function mapper)                    | 对包装对象进行函数运算                                |
| flatMap ()                               | 将一个二维的Optional对象映射成一个一维的对象                 |

