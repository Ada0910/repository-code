package com.ada.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ada.spring.anno.MyAutowired;
import com.ada.spring.anno.MyRequestMapping;
import com.ada.spring.anno.MyRequestParam;
import com.ada.spring.service.IMyService;

//虽然，用法一样，但是没有功能
@com.ada.spring.anno.MyController
@MyRequestMapping("/demo")
public class MyController {

  	@MyAutowired
    private IMyService demoService;

	@MyRequestMapping("/query.*")
	public void query(HttpServletRequest req, HttpServletResponse resp,
					  @MyRequestParam("name") String name){
//		String result = demoService.get(name);
		String result = "My name is " + name;
		try {
			resp.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@MyRequestMapping("/add")
	public void add(HttpServletRequest req, HttpServletResponse resp,
					@MyRequestParam("a") Integer a, @MyRequestParam("b") Integer b){
		try {
			resp.getWriter().write(a + "+" + b + "=" + (a + b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@MyRequestMapping("/sub")
	public void add(HttpServletRequest req, HttpServletResponse resp,
					@MyRequestParam("a") Double a, @MyRequestParam("b") Double b){
		try {
			resp.getWriter().write(a + "-" + b + "=" + (a - b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@MyRequestMapping("/remove")
	public String  remove(@MyRequestParam("id") Integer id){
		return "" + id;
	}

}
