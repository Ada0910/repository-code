在Spring框架中，`CommandLineRunner`和`ApplicationRunner`接口用于在Spring Boot应用程序启动后执行一些逻辑，它们都提供了一种方式来在Spring Boot应用启动时自动运行代码。这两个接口主要用于执行一些初始化操作、启动任务、或者任何在应用启动时需要完成的任务。

# 1. `CommandLineRunner` 接口

`CommandLineRunner`接口定义了一个`run()`方法，该方法会在Spring Boot应用程序启动后自动执行，并且传递命令行参数（如果有的话）。

接口定义

```block
javaCopy Code
public interface CommandLineRunner {
    void run(String... args) throws Exception;
}

```

典型用法

当你实现`CommandLineRunner`接口时，你可以在`run()`方法中执行任何初始化任务。`String... args`是从命令行传递给应用程序的参数，这可以用于在应用启动时做一些条件判断或者其他操作。

示例代码：

假设我们要在应用启动时打印一些日志，或者基于命令行参数做一些初始化工作，可以像这样实现：

```block
javaCopy Code
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // 处理命令行参数
        System.out.println("Application started with command-line arguments: ");
        for (String arg : args) {
            System.out.println(arg);
        }
        
        // 可以在这里执行应用启动时需要做的初始化工作
        System.out.println("Application initialized!");
    }
}

```

配置与执行：

- 如果你使用`@Component`注解将`MyCommandLineRunner`标注为Spring Bean，Spring Boot会自动发现并执行`run()`方法。

- `String... args`表示应用启动时的命令行参数，你可以在`run()`方法中使用这些参数执行相应的操作。

在启动应用时，你可以通过命令行传递参数，例如：

```block
bashCopy Code
java -jar your-application.jar arg1 arg2 arg3

```

然后，你会看到类似下面的输出：

```block
Copy Code
Application started with command-line arguments:
arg1
arg2
arg3
Application initialized!

```

# 2. `ApplicationRunner` 接口

`ApplicationRunner`接口与`CommandLineRunner`类似，不同之处在于它接收的参数类型不同。`ApplicationRunner`接口的`run()`方法接受的是一个`ApplicationArguments`类型的参数，而不是简单的字符串数组。`ApplicationArguments`提供了更多关于命令行参数的详细信息，能够更方便地处理命令行输入。

接口定义

```block
javaCopy Code
public interface ApplicationRunner {
    void run(ApplicationArguments args) throws Exception;
}

```

`ApplicationArguments`类`ApplicationArguments`类提供了一些方法来获取命令行参数，具体方法如下：

- `getSourceArgs()`: 获取原始的命令行参数（即`String[]`）。

- `getNonOptionArgs()`: 获取没有被指定为选项的参数。

- `getOptionNames()`: 获取所有选项的名称。

- `getOptionValues(String name)`: 获取指定选项名称的值。

示例代码：

假设我们要获取命令行参数，并且输出命令行中的选项和非选项参数：

```block
javaCopy Code
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 打印所有命令行参数
        System.out.println("Application started with command-line arguments:");
        
        // 打印所有的命令行选项参数
        args.getOptionNames().forEach(option -> {
            System.out.println("Option: " + option + " = " + args.getOptionValues(option));
        });

        // 打印非选项参数
        args.getNonOptionArgs().forEach(nonOption -> {
            System.out.println("Non-option argument: " + nonOption);
        });

        System.out.println("Application initialized!");
    }
}

```

配置与执行：

- `@Component`注解让`MyApplicationRunner`成为一个Spring Bean，Spring Boot会自动调用`run()`方法。

- `ApplicationArguments`提供了更强大的命令行参数解析功能，使得处理命令行输入更加灵活。

假设你启动应用时传入了以下命令行参数：

```block
bashCopy Code
java -jar your-application.jar --option1=value1 --option2=value2 arg1 arg2

```

输出将会是：

```block
Copy Code
Application started with command-line arguments:
Option: option1 = [value1]
Option: option2 = [value2]
Non-option argument: arg1
Non-option argument: arg2
Application initialized!

```

# 3. 总结比较：`CommandLineRunner` vs `ApplicationRunner`

- `CommandLineRunner`：

    - 接收命令行参数作为`String... args`数组。

    - 使用简单，适合处理简单的命令行参数。

- `ApplicationRunner`：

    - 接收命令行参数作为`ApplicationArguments`对象。

    - 提供了更多的功能，能够获取更丰富的命令行参数信息，包括选项参数与非选项参数的区分。

# 4. 执行顺序

- 如果应用中同时实现了`CommandLineRunner`和`ApplicationRunner`，Spring Boot会按字典顺序（通常是实现的类名的字典顺序）执行它们。

- 如果你想控制执行顺序，可以通过`@Order`注解或实现`org.springframework.core.annotation.Order`接口来设置执行顺序。

## 5. 在实际项目中的使用场景

- **初始化任务**：比如在应用启动时加载一些配置数据，或者执行数据库连接检查。

- **调试日志**：打印日志来帮助开发人员调试应用启动过程。

- **外部服务连接**：连接外部API或服务，进行认证，初始化数据等。

- **命令行工具**：Spring Boot的命令行工具通常用`CommandLineRunner`或`ApplicationRunner`来处理命令行输入，执行命令。

这些接口为开发者提供了在应用启动时执行特定操作的灵活方式，特别适用于Spring Boot应用中的初始化任务。

