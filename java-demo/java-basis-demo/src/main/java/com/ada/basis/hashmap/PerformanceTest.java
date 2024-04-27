package com.ada.basis.hashmap;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * hashmap性能测试
 *
 * 详细的参数可以参考下面的文章：
 * https://juejin.cn/post/7031008727645831176
 *
 * <p/>
 *
 * @Date: 2024/4/27 23:13
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 1s
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 1s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例
public class PerformanceTest {
	static Map<Integer, String> map = new HashMap() {{
		// 添加数据
		for (int i = 0; i < 100; i++) {
			put(i, "val:" + i);
		}
	}};

	public static void main(String[] args) throws RunnerException {
		// 启动基准测试
		Options opt = new OptionsBuilder()
				.include(PerformanceTest.class.getSimpleName()) // 要导入的测试类
				.output("D:\\Code\\repository-code\\java-demo\\java-basis-demo\\src\\main\\java\\com\\ada\\basis\\hashmap\\jmh-map.log") // 输出测试结果的文件
				//.resultFormat(ResultFormatType.JSON)
				.build();
		new Runner(opt).run(); // 执行测试
	}

	@Benchmark
	public void entrySet() {
		// 遍历
		Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, String> entry = iterator.next();
			Integer k = entry.getKey();
			String v = entry.getValue();
		}
	}

	@Benchmark
	public void forEachEntrySet() {
		// 遍历
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			Integer k = entry.getKey();
			String v = entry.getValue();
		}
	}

	@Benchmark
	public void keySet() {
		// 遍历
		Iterator<Integer> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Integer k = iterator.next();
			String v = map.get(k);
		}
	}

	@Benchmark
	public void forEachKeySet() {
		// 遍历
		for (Integer key : map.keySet()) {
			Integer k = key;
			String v = map.get(k);
		}
	}

	@Benchmark
	public void lambda() {
		// 遍历
		map.forEach((key, value) -> {
			Integer k = key;
			String v = value;
		});
	}

	@Benchmark
	public void streamApi() {
		// 单线程遍历
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