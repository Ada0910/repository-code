package com.ada.spring.anno.ordinary;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 导入普通类
 * 
 *
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 17:13
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class OrdinaryClassImport {
	public void printName() {
		System.out.println("类名 ：" + Thread.currentThread().getStackTrace()[1].getClassName());
	}
}
