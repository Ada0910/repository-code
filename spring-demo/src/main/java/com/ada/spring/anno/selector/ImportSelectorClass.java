package com.ada.spring.anno.selector;

import org.springframework.context.annotation.Configuration;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 通过实现ImportSelector的接口进行的导入
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 17:58
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
@Configuration
public class ImportSelectorClass {
	public void printName() {
		System.out.println("类名 ：" + Thread.currentThread().getStackTrace()[1].getClassName());
	}
}
