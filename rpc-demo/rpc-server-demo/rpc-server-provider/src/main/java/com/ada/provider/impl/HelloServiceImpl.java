package com.ada.provider.impl;

import com.ada.api.dto.User;
import com.ada.api.service.IHelloService;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 接口实现类
 * <p/>
 *
 * @Date: 2022/10/10 23:38
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class HelloServiceImpl implements IHelloService {


	@Override
	public String sayHello(String content) {
		System.out.println("HelloServiceImpl中的sayHello：" + content);
		return "sayHello  " + content;
	}

	@Override
	public String saveUser(User user) {
		System.out.println("HelloServiceImpl中的saveUser：" + user);
		return "SUCCESS";
	}
}
