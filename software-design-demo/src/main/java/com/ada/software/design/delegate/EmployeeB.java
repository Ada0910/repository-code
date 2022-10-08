package com.ada.software.design.delegate;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 员工B
 * <p/>
 *
 * @Date: 2022/10/8 22:17
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class EmployeeB implements IEmployee {
	@Override
	public void doing(String command) {
		System.out.println("我是员工 B，我现在开始干" + command + "工作");
	}
}
