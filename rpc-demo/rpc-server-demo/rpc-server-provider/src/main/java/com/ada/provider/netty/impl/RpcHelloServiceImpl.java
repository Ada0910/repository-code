package com.ada.provider.netty.impl;


import com.ada.api.netty.api.IRpcHelloService;

public class RpcHelloServiceImpl implements IRpcHelloService {

	public String hello(String name) {
		System.out.println("Hello " + name + "!");
		return "Hello " + name + "!";
	}

}  
