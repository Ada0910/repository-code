package com.ada.rabbitmq.demo;

import com.ada.rabbitmq.demo.provider.MyProvider;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/12/4 16:42
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {
	@Autowired
	MyProvider provider;

	@Test
	public void contextLoads() {
		provider.send();
	}

}
