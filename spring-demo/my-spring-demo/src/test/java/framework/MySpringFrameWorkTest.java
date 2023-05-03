package framework;

import com.ada.framework.context.MyApplicationContext;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 手写Spring 测试类
 * <p/>
 *
 * @Date: 2023/5/3 15:14
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MySpringFrameWorkTest {
	public static void main(String[] args) {

		MyApplicationContext context = new MyApplicationContext("application.properties");
		System.out.println(context);
	}
}
