package com.ada.spring.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ada.framework.webmvc.servlet.MyModelAndView;
import com.ada.spring.anno.MyAutowired;
import com.ada.spring.anno.MyRequestMapping;
import com.ada.spring.anno.MyRequestParam;
import com.ada.spring.service.IModifyService;
import com.ada.spring.service.IMyService;

//虽然，用法一样，但是没有功能
@com.ada.spring.anno.MyController
@MyRequestMapping("/demo")
public class MyController {

  	@MyAutowired
    private IMyService demoService;

	@MyAutowired
	IModifyService modifyService;

	@MyRequestMapping("/query/name")
	public void query(HttpServletRequest req, HttpServletResponse resp, @MyRequestParam("name")String name){
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

	@MyRequestMapping("/add*.json")
	public MyModelAndView add(HttpServletRequest request, HttpServletResponse response,
	                          @MyRequestParam("name") String name, @MyRequestParam("addr") String addr) {
		String result = null;
		try {
			result = modifyService.add(name, addr);
			return out(response, result);
		} catch (Exception e) {
//			e.printStackTrace();
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("detail", e.getMessage());
//			System.out.println(Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]",""));
			model.put("stackTrace", Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", ""));
			return new MyModelAndView("500", model);
		}

	}
	private MyModelAndView out(HttpServletResponse resp,String str){
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
