package com.ada.distributed.session.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 分布式session 不一致的解决方案
 *
 * 存在redis中来解决分布式session不一致的问题
 *
 * <p/>
 *
 * @Date: 2024/4/22 23:48
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Controller
public class TestSessionController {

	/**
	 * 说明：
	 *
	 * 	http://www.ada.com/createSession?name=ada
	 *
	 * nginx 有做如下配置：
	 *	 #默认使用轮询,
	 *	 upstream backserver{
	 *		 server 127.0.0.1:8080;
	 *		 server 127.0.0.1:8081;
	 *     }
	 *	 #修改server中的local
	 *	 location / {
	 *		 proxy_pass  http://backserver;
	 *		 index  index.html index.htm;
	 *     }
	 *
	 * 监听了80端口，因为nginx做了负载，所以可以直接访问来获取
	 * http://www.ada.com/getSession
	 *
	 * 结果：模拟分布式的获取，但是两次的获取的值都是一样
	 *
	 */

	@Value("${server.port}")
	private Integer projectPort;// 项目端口

	@RequestMapping("/createSession")
	public String createSession(HttpSession session, String name) {
		session.setAttribute("name", name);
		System.out.println("当前项目端口：" + projectPort + " 当前sessionId :" + session.getId() + "在Session中存入成功！");
		return "当前项目端口：" + projectPort + " 当前sessionId :" + session.getId() + "在Session中存入成功！";
	}

	@RequestMapping("/getSession")
	public String getSession(HttpSession session) {
		System.out.println("当前项目端口：" + projectPort + " 当前sessionId :" + session.getId() + "  获取的姓名:" + session.getAttribute("name"));
		return "当前项目端口：" + projectPort + " 当前sessionId :" + session.getId() + "  获取的姓名:" + session.getAttribute("name");
	}
}
