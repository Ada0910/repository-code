package com.ada.distributed.session2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 分布式session 不一致的解决方案
 * <p/>
 *
 * @Date: 2024/4/22 23:48
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Controller
public class TestSession2Controller {

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
