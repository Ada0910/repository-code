在 Java 中，`ClassLoader` 是负责加载类文件的核心组件。一般来说，`ClassLoader` 有以下几种获取方式：

# 1. 使用 `Class` 的 `getClassLoader()` 方法

每个 Java 类都有一个 `Class` 对象，可以通过该对象的 `getClassLoader()` 方法来获取该类的加载器。这个方法返回加载该类的 `ClassLoader`。

- **示例：**

```block
javaCopy Code
Class<?> clazz = SomeClass.class;
ClassLoader classLoader = clazz.getClassLoader();
System.out.println(classLoader);

```

在这个示例中，`SomeClass.class` 对象的类加载器通过 `getClassLoader()` 方法获得。

- 对于系统类（比如 `java.lang.String`），它会返回 `null`，因为这些类是由引导类加载器（Bootstrap ClassLoader）加载的，而它没有对应的 `ClassLoader` 实例。

# 2. 使用 `Thread` 的 `getContextClassLoader()` 方法

Java 的 `Thread` 类提供了 `getContextClassLoader()` 方法，返回当前线程的上下文类加载器。这个方法是多线程编程中比较常用的，通常用于在类加载过程中，确保可以访问当前线程的类加载器。

- **示例：**

```block
ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
System.out.println(contextClassLoader);

```

这个方法通常用于框架中（如 Java EE 容器中），因为它允许动态地改变类加载器，以便访问特定的类路径。

# 3. 使用 `ClassLoader.getSystemClassLoader()ClassLoader.getSystemClassLoader()` 方法

返回系统类加载器（通常是应用程序类加载器）。它是通过系统类路径加载类的。

- **示例：**

```block
javaCopy Code
ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
System.out.println(systemClassLoader);

```

系统类加载器通常是 `Application ClassLoader`，它会根据类路径加载类，通常加载放在 `CLASSPATH` 环境变量中指定的类。

# 4. 使用 `ClassLoader` 的子类（自定义类加载器）

你还可以通过继承 `ClassLoader` 类来实现自定义的类加载器。通过自定义类加载器，可以定制如何从文件系统、网络等其他地方加载类。

- **示例：**

```block
javaCopy Code
class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // 自定义类加载逻辑
        return super.loadClass(name); // 调用父类的方法
    }
}

MyClassLoader myClassLoader = new MyClassLoader();
Class<?> clazz = myClassLoader.loadClass("com.example.MyClass");
System.out.println(clazz);

```

这种方法是扩展 Java 类加载机制的一种方式，允许你在类加载过程中加入自定义的逻辑。

# 5. **引导类加载器（Bootstrap ClassLoader）**

引导类加载器是 Java 的最顶层类加载器，它负责加载 Java 标准库中的类（例如 `java.lang.*` 中的类）。引导类加载器并没有直接的 `ClassLoader` 对象，它是由 JVM 内部实现的，通常不能通过 `getClassLoader()` 获取（对于类库中的类，它返回 `null`）。

- 引导类加载器加载的类是来自 JRE 的 `rt.jar` 或 `lib/ext` 目录等。

# 总结

Java 中 `ClassLoader` 的获取方式主要有：

1. 通过类的 `getClassLoader()` 方法：获取加载该类的类加载器。

1. 通过线程的 `getContextClassLoader()` 方法：获取当前线程的上下文类加载器。

1. 通过 `ClassLoader.getSystemClassLoader()` 方法：获取系统类加载器。

1. **通过自定义类加载器**：继承 `ClassLoader` 类，创建自定义的类加载器。

1. **引导类加载器（Bootstrap ClassLoader）**：由 JVM 自动提供，用于加载核心类库。









