package com.ada.jvm.stack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 模拟堆的Eden区和S0/S1的分配、回收情况
 *
 * 内存参数设置为：-Xms20M -Xmx20M
 * <p/>
 *
 * @Date: 2022/11/6 10:41
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@RestController
public class HeapController {
	List<Person> list = new ArrayList<Person>();

	@GetMapping("/heap")
	public String heap() throws Exception {
		while (true) {
			System.out.println("coming!");
			list.add(new Person());
			Thread.sleep(1);
		}
	}
}

