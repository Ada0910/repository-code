package com.ada.spring.anno.ordinary;

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
 * 导入普通类测试类
 * 
 * 源码的入口可看：ConfigurationClassParser.java中的doProcessConfigurationClass()
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 17:15
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringDemoApplication.class)
public class OrdinaryClassImportTest {
	
	@Autowired
	OrdinaryClassImport ordinaryClassImport;
	
	@Test
	public void test(){
		ordinaryClassImport.printName();
	}
}
