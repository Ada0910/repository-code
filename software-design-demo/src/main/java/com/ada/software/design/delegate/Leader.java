package com.ada.software.design.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/8 22:21
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Leader implements IEmployee {

	private Map<String, IEmployee> map = new HashMap<>();

	public Leader() {
		map.put("加密", new EmployeeA());
		map.put("登录", new EmployeeB());
	}

	// 项目经理不干活
	@Override
	public void doing(String command) {
		map.get(command).doing(command);
	}
}
