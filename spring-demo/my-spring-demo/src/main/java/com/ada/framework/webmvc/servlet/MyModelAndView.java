package com.ada.framework.webmvc.servlet;

import java.util.Map;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/3 21:26
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class  MyModelAndView {
	String viewName;

	Map<String, ?> model;

	public MyModelAndView(String viewName) {
		this.viewName = viewName;
	}

	public MyModelAndView(String viewName, Map<String, ?> model) {
		this.viewName = viewName;
		this.model = model;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Map<String, ?> getModel() {
		return model;
	}

	public void setModel(Map<String, ?> model) {
		this.model = model;
	}
}
