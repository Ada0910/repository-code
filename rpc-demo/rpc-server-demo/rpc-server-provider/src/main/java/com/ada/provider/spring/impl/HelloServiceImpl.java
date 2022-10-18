package com.ada.provider.spring.impl;

import com.ada.api.dto.User;
import com.ada.api.service.IHelloService;
import com.ada.provider.spring.anno.RpcService;

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
@RpcService(value = IHelloService.class,version = "2.0")
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

	public HelloServiceImpl() {
		super();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
}
