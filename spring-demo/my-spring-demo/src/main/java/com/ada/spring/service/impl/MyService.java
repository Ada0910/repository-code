package com.ada.spring.service.impl;

import com.ada.spring.service.IMyService;

/**
 * 核心业务逻辑
 */
@com.ada.spring.anno.MyService
public class MyService implements IMyService {

	public String get(String name) {
		return "My name is " + name;
	}

}
