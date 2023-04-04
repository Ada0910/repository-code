package com.ada.spring.anno.selector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ada.spring.SpringDemoApplication;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 实现一个ImportSelectors的接口方法用于导入（测试类）
 * 源码的入口可看：ConfigurationClassParser.java中的doProcessConfigurationClass()
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 17:44
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringDemoApplication.class)
public class ImportSelectorClassTest {

	@Autowired
	ImportSelectorClass importSelectorClass;

	@Test
	public void test(){
		importSelectorClass.printName();
	}
}
