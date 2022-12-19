# JMH 
# 测试类例子
```
/**
 * 参数说明：
 * BenchmarkMode : Mode 表示 JMH 进行 Benchmark 时所使用的模式。通常是测量的维度不同，或是测量的方式不同。目前 JMH 共有四种模式：
 （1）Throughput: 整体吞吐量，例如“1秒内可以执行多少次调用”，单位是操作数/时间。
 （2） AverageTime: 调用的平均时间，例如“每次调用平均耗时xxx毫秒”，单位是时间/操作数。
 （3）SampleTime: 随机取样，最后输出取样结果的分布，例如“99%的调用在xxx毫秒以内，99.99%的调用在xxx毫秒以内”。
  (4)SingleShotTime: 以上模式都是默认一次 iteration 是 1s，唯有 SingleShotTime 是只运行一次。往往同时把 warmup 次数设为0，用于测试冷启动时的性能
 *
 * OutputTimeUnit : 输出的时间单位
 * Warmup : 预热，因为 JVM 的 JIT 机制的存在，如果某个函数被调用多次之后，JVM 会尝试将其编译成为机器码从而提高执行速度
 * Measurement : 指定迭代的次数，每次迭代的运行时间和每次迭代测试调用的数量
 * Fork : 如果 fork 数是2的话，则 JMH 会 fork 出两个进程来进行测试
 * State : JMH 测试类必须使用 @State 注解，它定义了一个类实例的生命周期,
 (1)Scope.Thread：默认的 State，每个测试线程分配一个实例；
 (2)Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能；
 (3)Scope.Group：每个线程组共享一个实例；
 *
 */
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 1s
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 1s
@Fork(1) // fork 1 个线程
@State(Scope.Thread)// 每个测试线程一个实例
public class HashMapTest {

	static Map<Integer, String> map = new HashMap() {
		{
			for (int i = 0; i < 100; i++) {
				put(i, "val:" + i);
			}
		}

	};

	public static void main(String[] args) throws RunnerException {
		// 启动基准测试
		Options options = new OptionsBuilder().include(HashMapTest.class.getSimpleName()).output("C:\\Users\\UMS-AUG\\Desktop\\jmh-map.log").build();
		new Runner(options).run();
	}

	/**
	 *  推荐使用，EntrySet 方式
	 */
	@Benchmark
	public void entrySet() {
		Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, String> entry = iterator.next();
			Integer key = entry.getKey();
			String value = entry.getValue();
		}
	}


	/**
	 * @Benchmark 注解，加上之后就能测试该方法的执行时间
	 */
	@Benchmark
	public void forEachEntrySet() {
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			Integer key = entry.getKey();
			String value = entry.getValue();
		}
	}


	/**
	 *  KEYSet方法，不推荐
	 */
	@Benchmark
	public void keySet() {
		Iterator<Integer> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			String value = map.get(key);
		}
	}

	@Benchmark
	public void forEachKeySet() {
		for (Integer key : map.keySet()) {
			String value = map.get(key);
		}

	}

	@Benchmark
	public void lambda() {
		map.forEach((key, value) -> {
			Integer i = key;
			String v = value;
		});
	}

	@Benchmark
	public void streamApi() {
		map.entrySet().stream().forEach((entry) -> {
			Integer k = entry.getKey();
			String v = entry.getValue();
		});
	}


	public void parallelStreamApi() {
		// 多线程遍历
		map.entrySet().parallelStream().forEach((entry) -> {
			Integer k = entry.getKey();
			String v = entry.getValue();
		});
	}

}
备注：转自地址[Java中文社群](https://mp.weixin.qq.com/s/zQBN3UvJDhRTKP6SzcZFKw)
```

## 参数说明
- @BenchmarkMode
Mode 表示 JMH 进行 Benchmark 时所使用的模式。通常是测量的维度不同，或是测量的方式不同。目前 JMH 共有四种模式：

Throughput: 整体吞吐量，例如“1秒内可以执行多少次调用”，单位是操作数/时间。
AverageTime: 调用的平均时间，例如“每次调用平均耗时xxx毫秒”，单位是时间/操作数。
SampleTime: 随机取样，最后输出取样结果的分布，例如“99%的调用在xxx毫秒以内，99.99%的调用在xxx毫秒以内”。
SingleShotTime: 以上模式都是默认一次 iteration 是 1s，唯有 SingleShotTime 是只运行一次。往往同时把 warmup 次数设为0，用于测试冷启动时的性能。

- @OutputTimeUnit
输出的时间单位。

- @Iteration
Iteration 是 JMH 进行测试的最小单位。在大部分模式下，一次 iteration 代表的是一秒，JMH 会在这一秒内不断调用需要 Benchmark 的方法，然后根据模式对其采样，计算吞吐量，计算平均执行时间等。

- @WarmUp
Warmup 是指在实际进行 Benchmark 前先进行预热的行为。

为什么需要预热？因为 JVM 的 JIT 机制的存在，如果某个函数被调用多次之后，JVM 会尝试将其编译成为机器码从而提高执行速度。为了让 Benchmark 的结果更加接近真实情况就需要进行预热。

- @State
类注解，JMH 测试类必须使用 @State 注解，它定义了一个类实例的生命周期，可以类比 Spring Bean 的 Scope。由于 JMH 允许多线程同时执行测试，不同的选项含义如下：

Scope.Thread：默认的 State，每个测试线程分配一个实例；
Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能；
Scope.Group：每个线程组共享一个实例；


- @Fork
进行 fork 的次数。如果 fork 数是2的话，则 JMH 会 fork 出两个进程来进行测试。

- @Meansurement
提供真正的测试阶段参数。指定迭代的次数，每次迭代的运行时间和每次迭代测试调用的数量(通常使用 @BenchmarkMode(Mode.SingleShotTime) 测试一组操作的开销——而不使用循环)

- @Setup
方法注解，会在执行 benchmark 之前被执行，正如其名，主要用于初始化。

- @TearDown
方法注解，与@Setup 相对的，会在所有 benchmark 执行结束以后执行，主要用于资源的回收等。

- @Setup/@TearDown注解使用Level参数来指定何时调用fixture：

名称	描述
Level.Trial	默认level。全部benchmark运行(一组迭代)之前/之后
Level.Iteration	一次迭代之前/之后(一组调用)
Level.Invocation	每个方法调用之前/之后(不推荐使用，除非你清楚这样做的目的)


-  @Benchmark
方法注解，表示该方法是需要进行 benchmark 的对象。

-  @Param
成员注解，可以用来指定某项参数的多种情况。特别适合用来测试一个函数在不同的参数输入的情况下的性能。@Param 注解接收一个String数组，在 @Setup 方法执行前转化为为对应的数据类型。多个 @Param 注解的成员之间是乘积关系，譬如有两个用 @Param 注解的字段，第一个有5个值，第二个字段有2个值，那么每个测试方法会跑5*2=10次。