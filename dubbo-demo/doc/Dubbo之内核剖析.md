# Java的SPI

了解 Dubbo 里面的 SPI 机制之前，我们先了解下 Java 提供的 SPI（service provider interface）机制，SPI 是 JDK 内置的一种服务提供发现机制。目前市面上有很多框架都是用它来做服务的扩展发现。简单来说，它是一种动态替换发现的机制。举个简单
的例子，我们想在运行时动态给你只需要添加一个实现，然后把新的实现描述给 JDK 知道就行了

需要遵循的标准

（1） 需要在 classpath 下创建一个目录，该目录命名必须是：META-INF/service
（2） 在该目录下创建一个 properties 文件，该文件需要满足以下几个条件
a.文件名必须是扩展的接口的全路径名称
b.文件内部描述的是该扩展接口的所有实现类
c.文件的编码格式是 UTF-8
(3) 通过 java.util.ServiceLoader 的加载机制来发现

例如：
![image.png](./assets/1677239008259-image.png)

缺点：
（1）JDK 标准的 SPI会一次性加实例化扩展点的所有实现，什意思呢?就是如果你在 META-INF/service 下的文件里面加了N个实现类，那么IDK 启动的时候都会一次性全部加载。那么如果有的扩展点实现初始化很耗时或者如果有些实现类并没有用到。那么会很浪费资源
（2）如果扩展点加载失败，会导致调用方报错，而且这个错误很难定位到是这个原因

## JDBC的SPI源码

找到Driver类：

```
static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException var1) {
            throw new RuntimeException("Can't register driver!");
        }
    }
```

初始化静态方法：

```
static {
        loadInitialDrivers();
        println("JDBC DriverManager initialized");
    }
```

loadInitialDrivers初始化

```
private static void loadInitialDrivers() {
        String drivers;
        try {
            drivers = AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty("jdbc.drivers");
                }
            });
        } catch (Exception ex) {
            drivers = null;
        }
        // If the driver is packaged as a Service Provider, load it.
        // Get all the drivers through the classloader
        // exposed as a java.sql.Driver.class service.
        // ServiceLoader.load() replaces the sun.misc.Providers()

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {

                ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
                Iterator<Driver> driversIterator = loadedDrivers.iterator();

                /* Load these drivers, so that they can be instantiated.
                 * It may be the case that the driver class may not be there
                 * i.e. there may be a packaged driver with the service class
                 * as implementation of java.sql.Driver but the actual class
                 * may be missing. In that case a java.util.ServiceConfigurationError
                 * will be thrown at runtime by the VM trying to locate
                 * and load the service.
                 *
                 * Adding a try catch block to catch those runtime errors
                 * if driver not available in classpath but it's
                 * packaged as service and that service is there in classpath.
                 */
                try{
                    while(driversIterator.hasNext()) {
                        driversIterator.next();
                    }
                } catch(Throwable t) {
                // Do nothing
                }
                return null;
            }
        });

        println("DriverManager.initialize: jdbc.drivers = " + drivers);

        if (drivers == null || drivers.equals("")) {
            return;
        }
        String[] driversList = drivers.split(":");
        println("number of Drivers:" + driversList.length);
        for (String aDriver : driversList) {
            try {
                println("DriverManager.Initialize: loading " + aDriver);
                Class.forName(aDriver, true,
                        ClassLoader.getSystemClassLoader());
            } catch (Exception ex) {
                println("DriverManager.Initialize: load failed: " + ex);
            }
        }
    }
```

# Spring的SPI

Spring Boot在初始化的时候，有以下的代码，

SpringApplication.class有以下方法：

```
private <T> Collection<T> getSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes, Object... args) {
        ClassLoader classLoader = this.getClassLoader();
        Set<String> names = new LinkedHashSet(SpringFactoriesLoader.loadFactoryNames(type, classLoader));
        List<T> instances = this.createSpringFactoriesInstances(type, parameterTypes, classLoader, args, names);
        AnnotationAwareOrderComparator.sort(instances);
        return instances;
    }
```

重点关注以下的方法：

```
loadFactoryNames
```

```
public static List<String> loadFactoryNames(Class<?> factoryType, @Nullable ClassLoader classLoader) {
        ClassLoader classLoaderToUse = classLoader;
        if (classLoader == null) {
            classLoaderToUse = SpringFactoriesLoader.class.getClassLoader();
        }

        String factoryTypeName = factoryType.getName();
        return (List)loadSpringFactories(classLoaderToUse).getOrDefault(factoryTypeName, Collections.emptyList());
    }
```

```
private static Map<String, List<String>> loadSpringFactories(ClassLoader classLoader) {
        Map<String, List<String>> result = (Map)cache.get(classLoader);
        if (result != null) {
            return result;
        } else {
            HashMap result = new HashMap();

            try {
                Enumeration urls = classLoader.getResources("META-INF/spring.factories");

                while(urls.hasMoreElements()) {
                    URL url = (URL)urls.nextElement();
                    UrlResource resource = new UrlResource(url);
                    Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                    Iterator var6 = properties.entrySet().iterator();

                    while(var6.hasNext()) {
                        Entry<?, ?> entry = (Entry)var6.next();
                        String factoryTypeName = ((String)entry.getKey()).trim();
                        String[] factoryImplementationNames = StringUtils.commaDelimitedListToStringArray((String)entry.getValue());
                        String[] var10 = factoryImplementationNames;
                        int var11 = factoryImplementationNames.length;

                        for(int var12 = 0; var12 < var11; ++var12) {
                            String factoryImplementationName = var10[var12];
                            ((List)result.computeIfAbsent(factoryTypeName, (key) -> {
                                return new ArrayList();
                            })).add(factoryImplementationName.trim());
                        }
                    }
                }

                result.replaceAll((factoryType, implementations) -> {
                    return (List)implementations.stream().distinct().collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
                });
                cache.put(classLoader, result);
                return result;
            } catch (IOException var14) {
                throw new IllegalArgumentException("Unable to load factories from location [META-INF/spring.factories]", var14);
            }
        }
    }
```

## 如何自定一个Spring Boot的starter

代码见：distribute-demo模块，

```
ada-spring-boot-starter模块
```

# Dubbo的SPI

Dubbo的SPI扩展机制，有下面规则
（1）需要在resource目录下配置META-INF/dubbo
或者META-INF/dubbo/internal 或者META-INF/services,并基于SPI接口去创建一个文件
（2）文件名和接口名称保持一致，内容和SPI有差异，内容是KEY对应的value

Dubbo 针对的扩展点非常多，可以针对协议、拦截、集群、路由、负载均衡、序列化、容器… 几乎里面用到的所有功能，都可以实现自己的扩展，我觉得这个是 dubbo 比较强大的一点

## 扩展协议扩展点

（1）创建如下结构，添加META-INF.dubbo文件，类名和Dubbo提供的协议扩展点接口保持一致

（2）创建MyProtocol协议类
可以实现自己的协议，我们为了模拟协议产生了作用，修改了一个端口

## Dubbo的扩展原理实现

1.被加载的类是如何存储和使用的

```
public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Extension type == null");
        } else if (!type.isInterface()) {
            throw new IllegalArgumentException("Extension type (" + type + ") is not an interface!");
        } else if (!withExtensionAnnotation(type)) {
            throw new IllegalArgumentException("Extension type (" + type + ") is not an extension, because it is NOT annotated with @" + SPI.class.getSimpleName() + "!");
        } else {
            ExtensionLoader<T> loader = (ExtensionLoader)EXTENSION_LOADERS.get(type);
            if (loader == null) {
                EXTENSION_LOADERS.putIfAbsent(type, new ExtensionLoader(type));
                loader = (ExtensionLoader)EXTENSION_LOADERS.get(type);
            }

            return loader;
        }
    }
```

```
private ExtensionLoader(Class<?> type) {
        this.type = type;
        this.objectFactory = type == ExtensionFactory.class ? null : (ExtensionFactory)getExtensionLoader(ExtensionFactory.class).getAdaptiveExtension();
    }
```

```
public T getAdaptiveExtension() {
        Object instance = this.cachedAdaptiveInstance.get();
        if (instance == null) {
            if (this.createAdaptiveInstanceError != null) {
                throw new IllegalStateException("Failed to create adaptive instance: " + this.createAdaptiveInstanceError.toString(), this.createAdaptiveInstanceError);
            }

            synchronized(this.cachedAdaptiveInstance) {
                instance = this.cachedAdaptiveInstance.get();
                if (instance == null) {
                    try {
                        instance = this.createAdaptiveExtension();
                        this.cachedAdaptiveInstance.set(instance);
                    } catch (Throwable var5) {
                        this.createAdaptiveInstanceError = var5;
                        throw new IllegalStateException("Failed to create adaptive instance: " + var5.toString(), var5);
                    }
                }
            }
        }

        return instance;
    }
```

```java
public T getExtension(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Extension name == null");
        } else if ("true".equals(name)) {
            return this.getDefaultExtension();
        } else {
            Holder<Object> holder = this.getOrCreateHolder(name);
            Object instance = holder.get();
            if (instance == null) {
                synchronized(holder) {
                    instance = holder.get();
                    if (instance == null) {
                        instance = this.createExtension(name);
                        holder.set(instance);
                    }
                }
            }

            return instance;
        }
    }
```

```
private T createExtension(String name) {
        Class<?> clazz = (Class)this.getExtensionClasses().get(name);
        if (clazz == null) {
            throw this.findException(name);
        } else {
            try {
                T instance = EXTENSION_INSTANCES.get(clazz);
                if (instance == null) {
                    EXTENSION_INSTANCES.putIfAbsent(clazz, clazz.newInstance());
                    instance = EXTENSION_INSTANCES.get(clazz);
                }

                this.injectExtension(instance);
                Set<Class<?>> wrapperClasses = this.cachedWrapperClasses;
                Class wrapperClass;
                if (CollectionUtils.isNotEmpty(wrapperClasses)) {
                    for(Iterator var5 = wrapperClasses.iterator(); var5.hasNext(); instance = this.injectExtension(wrapperClass.getConstructor(this.type).newInstance(instance))) {
                        wrapperClass = (Class)var5.next();
                    }
                }

                return instance;
            } catch (Throwable var7) {
                throw new IllegalStateException("Extension instance (name: " + name + ", class: " + this.type + ") couldn't be instantiated: " + var7.getMessage(), var7);
            }
        }
    }
```

getExtensionClasses
这个方法，会查找指定目录/META-INF/dubbo || /META-INF/services 下对应的 type->也就是本次演示案例的 Protocol 的 properties 文件，然后扫描这个文件下的所有配置信息，然后保存到一个HashMap中（classes）,key=name（对应protocol文件中配置的myprotocol）,value =对应配置类的实例

```
private Map<String, Class<?>> getExtensionClasses() {
        Map<String, Class<?>> classes = (Map)this.cachedClasses.get();
        if (classes == null) {
            synchronized(this.cachedClasses) {
                classes = (Map)this.cachedClasses.get();
                if (classes == null) {
                    classes = this.loadExtensionClasses();
                    this.cachedClasses.set(classes);
                }
            }
        }

        return classes;
    }
```

injectExtension 方法
private T injectExtension(T instance) {
try {
if (this.objectFactory != null) {
Method[] var2 = instance.getClass().getMethods();
int var3 = var2.length;

```
for(int var4 = 0; var4 < var3; ++var4) {
                Method method = var2[var4];
                if (this.isSetter(method) && method.getAnnotation(DisableInject.class) == null) {
                    Class<?> pt = method.getParameterTypes()[0];
                    if (!ReflectUtils.isPrimitives(pt)) {
                        try {
                            String property = this.getSetterProperty(method);
                            Object object = this.objectFactory.getExtension(pt, property);
                            if (object != null) {
                                method.invoke(instance, object);
                            }
                        } catch (Exception var9) {
                            logger.error("Failed to inject via method " + method.getName() + " of interface " + this.type.getName() + ": " + var9.getMessage(), var9);
                        }
                    }
                }
            }
        }
    } catch (Exception var10) {
        logger.error(var10.getMessage(), var10);
    }

    return instance;
}
```

## Adaptive 自适应扩展点

什么叫自适应扩展点呢?我们先演示一个例子，在下面这个例子中，我们传入一个 Compiler 接口，它会返回一个
AdaptiveCompiler。这个就叫自适应。Compiler compiler=ExtensionLoader.getExtensionLoader(Compiler.class).getAdaptiveExtension0);System.out.println(compiler.getClass0);它是怎么实现的呢?我们根据返回的 AdaptiveCompiler 这个类，看到这个类上面有一个注解@Adaptive。这个就是一个自适应扩展点的标识。它可以修饰在类上，也可以修饰在方法上面。这两者有什么区别呢?简单来说，放在类上，说明当前类是一个确定的自适应扩展点的类。如果是放在方法级别，那么需要生成一个动态字节码，来进行转发。
比如拿 Protocol这个接口来说，它里面定义了 export 和 refer 两个抽象方法，这两个方法分别带有@Adaptive 的标识，标识是一个自适应方法。
我们知道 Protocol是一个通信协议的接口，具体有多种实现，那么这个时候选择哪一种呢?取决于我们在使用 dubbo 的时候所配置的协议名称。而这里的方法层面的 Adaptive 就决定了当前这个方法会采用何种协议来发布服务



