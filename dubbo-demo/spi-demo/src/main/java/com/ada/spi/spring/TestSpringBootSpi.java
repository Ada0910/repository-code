package com.ada.spi.spring;

import com.ada.starter.split.ISplitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * <b><code></code></b>
 * <p/>
 * Spring Boot starter 自定义测试类
 *
 * 如何自定一个starter，可以引用ada-spring-boot-starter模块
 *
 * <p/>
 *
 * @Date: 2023/2/26 13:45
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@SpringBootTest
public class TestSpringBootSpi {

	@Autowired
	ISplitService splitService;

	@Test
	public void splitStr(){
		System.out.println(splitService.split("my,first"));

	}
}
