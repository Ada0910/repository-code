package com.ada.provider.netty.impl;


import com.ada.api.netty.api.IRpcHelloService;

public class RpcHelloServiceImpl implements IRpcHelloService {

	public String hello(String name) {
		return "Hello " + name + "!";
	}

}  
