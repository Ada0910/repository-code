package com.ada.boot.starter;

import com.ada.boot.boot.starter.FormatTemplate;
import com.ada.boot.boot.starter.config.Properties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2024/5/19 16:09
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@SpringBootTest
public class FormatTemplateTest {

	Properties properties = new Properties();

	@Autowired
	public FormatTemplate template;

	@Test
	public void format() {
		String s = template.doFormat(properties);
		System.out.println(s);
	}
}
