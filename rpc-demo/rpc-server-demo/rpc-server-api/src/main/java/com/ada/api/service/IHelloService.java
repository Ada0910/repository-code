package com.ada.api.service;

import com.ada.api.dto.User;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/10 23:25
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public interface IHelloService {

	//打招呼
	String sayHello(String content);

	// 保存用户
	String saveUser(User user);
}
