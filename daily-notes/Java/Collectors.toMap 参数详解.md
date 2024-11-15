
  `Collectors.toMap` 是 Java Stream API 中用于将流 (`Stream`) 转换成 `Map` 的一个常用收集器（Collector）。这个方法有多个重载版本，每个版本都允许你定制 `Map` 的生成方式。

    下面是对 `Collectors.toMap` 的详细解释，包括其不同的重载形式和常见用途。

# 1. `Collectors.toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper)`

这是最常用的 `toMap` 版本，它需要两个参数：

- **keyMapper**：将流中的元素 `T` 转换成 `Map` 中的 `key`（类型为 `K`）的函数。

- **valueMapper**：将流中的元素 `T` 转换成 `Map` 中的 `value`（类型为 `V`）的函数。

### 示例



```block
import java.util.*;
import java.util.stream.Collectors;

public class ToMapExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        Map<Integer, String> wordMap = words.stream()
            .collect(Collectors.toMap(String::length, word -> word));

        // 打印出 Map 结果，键是字符串的长度，值是原字符串
        wordMap.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}

```

输出

```block
textCopy Code
5 -> apple
6 -> banana
6 -> cherry
4 -> date

```

在这个例子中，我们通过 `String::length` 获取每个字符串的长度作为 `key`，然后将原字符串作为 `value` 存入 `Map`。

# 2. `Collectors.toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper, BinaryOperator<V> mergeFunction)`

这个版本允许你指定一个合并函数 (`mergeFunction`)，该函数用于处理当两个元素有相同 `key` 时的冲突情况。如果多个流中的元素生成相同的 `key`，则会调用合并函数来决定如何处理这些冲突。

- **mergeFunction**：一个合并函数，它接受两个相同 `key` 的 `value`，并返回一个新的 `value`，这个新的 `value` 将会作为 `Map` 中的值。

### 示例

```block
import java.util.*;
import java.util.stream.Collectors;

public class ToMapWithMergeExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        Map<Integer, String> wordMap = words.stream()
            .collect(Collectors.toMap(
                String::length,           // keyMapper: 根据长度作为 key
                word -> word,             // valueMapper: 直接使用原字符串作为 value
                (existing, replacement) -> existing + ", " + replacement // mergeFunction
            ));

        // 打印出 Map 结果，长度相同的字符串会合并
        wordMap.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}

```

输出

```block
textCopy Code
5 -> apple
6 -> banana, cherry
4 -> date

```

在这个例子中，`banana` 和 `cherry` 都有相同的长度 `6`，所以它们被合并成一个值 `"banana, cherry"`

# 3. `Collectors.toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper, BinaryOperator<V> mergeFunction, Supplier<M> mapSupplier)`

这是最灵活的 `toMap` 版本，允许你指定一个自定义的 `Map` 类型。默认情况下，`toMap` 使用 `HashMap` 来存储结果，但你可以提供一个不同类型的 `Map` 供应器（`mapSupplier`）。

- **mapSupplier**：一个供应函数，返回一个新的 `Map` 实例。常用的实现可以是 `LinkedHashMap::new`（保证顺序）或 `TreeMap::new`（按自然顺序排序 `key`）。

### 示例



```block
import java.util.*;
import java.util.stream.Collectors;

public class ToMapWithCustomMapExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        Map<Integer, String> wordMap = words.stream()
            .collect(Collectors.toMap(
                String::length,           // keyMapper: 根据长度作为 key
                word -> word,             // valueMapper: 直接使用原字符串作为 value
                (existing, replacement) -> existing + ", " + replacement, // mergeFunction
                LinkedHashMap::new        // mapSupplier: 使用 LinkedHashMap 保证顺序
            ));

        // 打印出 Map 结果
        wordMap.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}

```

输出

```block
textCopy Code
5 -> apple
6 -> banana, cherry
4 -> date

```

在这个例子中，我们使用 `LinkedHashMap::new` 来确保 `Map` 的插入顺序与流的处理顺序一致。

### 参数详解

- **keyMapper**：一个函数，接收流中的元素并返回它对应的 `Map` 中的 `key`。

- **valueMapper**：一个函数，接收流中的元素并返回它对应的 `Map` 中的 `value`。

- **mergeFunction**：如果流中的元素有重复的 `key`，则调用这个合并函数来决定如何处理这些重复的元素。它接受两个相同 `key` 的值，并返回一个新的值。

- **mapSupplier**：一个提供器，返回一个新的 `Map` 实例。常见的实现包括 `HashMap::new`、`LinkedHashMap::new`、`TreeMap::new` 等。

# 使用场景

1. **基本转换**：你有一个流，想将它转换成一个 `Map`，键值对来自于流的元素。

1. **去重**：当流中有相同的 `key` 时，使用 `mergeFunction` 合并重复的 `value`。

1. 自定义 `Map` 类型：如果你需要控制结果 `Map` 的实现（例如使用 `LinkedHashMap` 保持插入顺序，或者使用 `TreeMap` 按照 `key` 排序）。

`Collectors.toMap` 是一个非常强大的工具，可以根据需求精确地控制流到 `Map` 的转换过程。

# 示例一：对Map<String,Map<String,List<String>>> 进行排序

```text
import java.util.*;
import java.util.stream.Collectors;

public class SortMapExample {

	public static void main(String[] args) {
		// 示例数据结构 Map<String, Map<String, List<String>>>
		Map<String, Map<String, List<String>>> data = new HashMap<>();

		// 内层Map: "B" -> ["B2", "B1"]
		Map<String, List<String>> mapB = new HashMap<>();
		mapB.put("B2", Arrays.asList("B22", "B21"));
		mapB.put("B1", Arrays.asList("B12", "B11"));

		// 内层Map: "A" -> ["A1", "A2"]
		Map<String, List<String>> mapA = new HashMap<>();
		mapA.put("A2", Arrays.asList("A22", "A21"));
		mapA.put("A1", Arrays.asList("A12", "A11"));

		// 外层Map
		data.put("B", mapB);
		data.put("A", mapA);

		// 对外层Map进行排序（按key排序），然后对内层Map排序，最后对List排序
		Map<String, Map<String, List<String>>> sortedData = data.entrySet().stream()
				// 外层Map按key排序
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						entry -> {
							// 内层Map按key排序
							Map<String, List<String>> innerMap = entry.getValue().entrySet().stream()
									.sorted(Map.Entry.comparingByKey())
									.collect(Collectors.toMap(
											Map.Entry::getKey,
											Map.Entry::getValue,
											(e1, e2) -> e1, // 防止key冲突
											LinkedHashMap::new // 保证有序
									));

							// 对每个List排序
							innerMap.forEach((key, list) -> list.sort(String::compareTo));

							return innerMap;
						},
						(e1, e2) -> e1, // 防止key冲突
						LinkedHashMap::new // 保证有序
				));

		// 打印结果
		sortedData.forEach((outerKey, innerMap) -> {
			System.out.println("Outer Key: " + outerKey);
			innerMap.forEach((innerKey, list) -> {
				System.out.println("  Inner Key: " + innerKey + ", List: " + list);
			});
		});
	}
}

```

# 示例二：只对Map<String,Map<String,List<String>>> 第一个key和List<String>进行排序

```text
import java.util.*;
import java.util.stream.Collectors;

public class SortMapExample {

	public static void main(String[] args) {
		// 示例数据结构 Map<String, Map<String, List<String>>>
		Map<String, Map<String, List<String>>> data = new HashMap<>();

		// 内层Map: "B" -> ["B2", "B1"]
		Map<String, List<String>> mapB = new HashMap<>();
		mapB.put("B2", Arrays.asList("31", "32"));
		mapB.put("B1", Arrays.asList("B12", "B11"));

		// 内层Map: "A" -> ["A1", "A2"]
		Map<String, List<String>> mapA = new HashMap<>();
		mapA.put("A2", Arrays.asList("A22", "A21"));
		mapA.put("A1", Arrays.asList("A12", "A11"));

		// 外层Map
		data.put("B", mapB);
		data.put("A", mapA);

		// 对外层Map进行排序（按key排序），然后对每个List排序
		Map<String, Map<String, List<String>>> sortedData = data.entrySet().stream()
				// 外层Map按key排序
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						entry -> {
							// 对每个List排序
							entry.getValue().forEach((key, list) -> list.sort(String::compareTo));
							return entry.getValue();
						},
						(e1, e2) -> e1, // 防止key冲突
						LinkedHashMap::new // 保证有序
				));

		// 打印结果
		sortedData.forEach((outerKey, innerMap) -> {
			System.out.println("Outer Key: " + outerKey);
			innerMap.forEach((innerKey, list) -> {
				System.out.println("  Inner Key: " + innerKey + ", List: " + list);
			});
		});
	}
}


```

