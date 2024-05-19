package com.ada.boot.starter;

import com.ada.starter.split.ISplitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *  ada-spring-boot-starter 测试类
 *
 *  1.引包
 *  2.配置文件（application.properties）  ada.age等
 *  3.使用
 * <p/>
 *
 * @Date: 2024/5/19 18:50
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@SpringBootTest
public class AdaSpringBootStarterTest {
	@Autowired
	ISplitService splitService;

	@Test
	public void splitStr(){
		System.out.println(splitService.split("my,first"));

	}
}
